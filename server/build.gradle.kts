plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.sqldelight)
    application
}

group = "com.ring.ring"
version = "1.0.0"
application {
    mainClass.set("com.ring.ring.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.server.html.builder)
    implementation(libs.ktor.server.sessions)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.kotlinx.datetime)
    implementation(libs.logback.classic)
    implementation(libs.sqlite.driver)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}

sqldelight {
    databases {
        create("LocalDb") {
            packageName.set("data.db")
            srcDirs("src/main/kotlin/com/ring/ring/")
        }
    }
}
