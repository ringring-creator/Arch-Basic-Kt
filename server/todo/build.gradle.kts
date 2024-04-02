plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ktor)
}

group = "com.ring.ring"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.server.data)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.html.builder)
    implementation(libs.ktor.server.sessions)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.cors)
    implementation(libs.kotlinx.datetime)
    implementation(libs.logback.classic)
}

kotlin {
    jvmToolchain(17)
}