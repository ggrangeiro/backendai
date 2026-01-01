plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.25"
    id("com.google.devtools.ksp") version "1.9.25-1.0.20"
    id("io.micronaut.application") version "4.6.1"
    id("com.gradleup.shadow") version "8.3.9"
    id("io.micronaut.test-resources") version "4.6.1"
    id("io.micronaut.aot") version "4.6.1"
}

version = "0.1"
group = "com.fit"

val kotlinVersion = project.properties.get("kotlinVersion") ?: "1.9.25"

repositories {
    mavenCentral()
}

dependencies {
    ksp("io.micronaut.data:micronaut-data-processor")
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.security:micronaut-security-annotations")
    ksp("io.micronaut.serde:micronaut-serde-processor")
    ksp("io.micronaut.validation:micronaut-validation-processor")

    implementation("io.micrometer:context-propagation")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    implementation("io.micronaut.flyway:micronaut-flyway")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.reactor:micronaut-reactor")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")



    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.flywaydb:flyway-mysql")
    runtimeOnly("org.yaml:snakeyaml")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")

    testImplementation("io.micronaut:micronaut-http-client")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    aotPlugins(platform("io.micronaut.platform:micronaut-platform:4.10.6"))
    aotPlugins("io.micronaut.security:micronaut-security-aot")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    implementation("org.mindrot:jbcrypt:0.4")
    //implementation("io.micronaut.gcp:micronaut-gcp-function-http")
    //implementation("com.google.cloud.functions:functions-framework-api")
    //implementation("com.google.cloud.sql:mysql-socket-factory-connector-j-8:1.15.2")
    //implementation("com.google.cloud.functions.invoker:java-function-invoker:1.3.1")
    implementation("io.micronaut:micronaut-http-server-netty")
}


application {
    mainClass = "com.fit.ApplicationKt"
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
}

micronaut {
    //runtime("google_function")
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.fit.*")
    }
    aot {
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = false
        deduceEnvironment = true
        optimizeNetty = false
        replaceLogbackXml = true
        configurationProperties.put("micronaut.security.jwks.enabled", "false")
    }
    testResources {
        sharedServer.set(false)
    }
}


tasks.register("stopGradle") {
    group = "custom"
    description = "Para os daemons do Gradle"
    doLast {
        exec {
            commandLine("./gradlew", "--stop")
        }
    }
}

tasks.register("cleanBuildNoTests") {
    group = "custom"
    description = "Executa clean build sem testes"
    doLast {
        exec {
            commandLine(
                "./gradlew",
                "clean",
                "build",
                "-x",
                "internalStartTestResourcesService",
                "-x",
                "test"
            )
        }
    }
}

tasks.register("arroz") {
    group = "custom"
    description = "Pipeline completo + runFunction"
    dependsOn("cleanBuildNoTests", "runFunction")
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}


