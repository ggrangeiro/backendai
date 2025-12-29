package com.fit.auth.infra

import io.micronaut.http.HttpHeaders.AUTHORIZATION
import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken

@Client("/")
interface AppClient {

    @Post("/login")
    suspend fun login(@Body credentials: UsernamePasswordCredentials): BearerAccessRefreshToken

    @Consumes(TEXT_PLAIN)
    @Get
    suspend fun home(@Header(AUTHORIZATION) authorization: String): String
}