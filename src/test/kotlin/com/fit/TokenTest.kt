package com.fit

import com.fit.auth.infra.persistence.repository.UserRepository
import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus.OK
import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.endpoints.TokenRefreshRequest
import io.micronaut.security.token.render.AccessRefreshToken
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.security.auth.callback.ConfirmationCallback.OK

@MicronautTest(rollback = false)
internal class OauthAccessTokenTest(
    @Client("/") private val client: HttpClient
) {

    @Inject
    lateinit var userRepository: UserRepository

    @Test
    fun verifyJWTAccessTokenRefreshWorks() = runTest {
        val username = "ARROZ"

        val creds = UsernamePasswordCredentials(username, "arroz")
        val loginRequest = HttpRequest.POST("/login", creds)

        val oldTokenCount = userRepository.count()

        val loginResponse = client.exchange(loginRequest, BearerAccessRefreshToken::class.java).awaitSingle()
        val rsp = loginResponse.body()!!

        delay(3000)

        assertEquals(oldTokenCount + 1, userRepository.count())

        assertNotNull(rsp.accessToken)
        assertNotNull(rsp.refreshToken)

        delay(1000)
        val refreshRequest = HttpRequest.POST(
            "/oauth/access_token",
            TokenRefreshRequest(TokenRefreshRequest.GRANT_TYPE_REFRESH_TOKEN, rsp.refreshToken)
        )

        val refreshHttpResponse = client.exchange(refreshRequest, AccessRefreshToken::class.java).awaitSingle()
        val refreshResponse = refreshHttpResponse.body()!!

        assertNotNull(refreshResponse.accessToken)
        assertNotEquals(rsp.accessToken, refreshResponse.accessToken)

        userRepository.deleteAll()
    }


    @Test
    fun uponSuccessfulAuthenticationAJsonWebTokenIsIssuedToTheUser() = runTest {
        val creds = UsernamePasswordCredentials("ARROZ", "Arroz")
        val loginRequest = HttpRequest.POST("/login", creds)

        val loginResponse =
            client.exchange(loginRequest, BearerAccessRefreshToken::class.java).awaitSingle()

        assertEquals(OK, loginResponse.status)

        val bearerAccessRefreshToken = loginResponse.body()!!
        assertEquals("ARROZ", bearerAccessRefreshToken.username)
        assertNotNull(bearerAccessRefreshToken.accessToken)
        assertTrue(JWTParser.parse(bearerAccessRefreshToken.accessToken) is SignedJWT)

        val accessToken = bearerAccessRefreshToken.accessToken

        val requestWithAuthorization = HttpRequest.GET<Any>("/")
            .accept(TEXT_PLAIN)
            .bearerAuth(accessToken)

        val securedResponse =
            client.exchange(requestWithAuthorization, String::class.java).awaitSingle()

        assertEquals(OK, securedResponse.status)
        assertEquals("sherlock", securedResponse.body())
    }
}
