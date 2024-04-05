plugins {
    kotlin("jvm")
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.serialization)
}

group = "com.ring.ring"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.sqlite.driver)
    implementation(libs.ktor.serialization.kotlinx.json)
}

kotlin {
    jvmToolchain(17)
}

sqldelight {
    databases {
        create("LocalDb") {
            packageName.set("data.db")
            srcDirs("src/main/kotlin/com/ring/ring/")
        }
    }
}
