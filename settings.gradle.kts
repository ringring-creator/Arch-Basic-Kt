rootProject.name = "Arch-Basic-KMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

include(":composeApp")
include(":server")
include("server:maintenance-session-service")
findProject(":server:maintenance-session-service")?.name = "maintenance-session-service"
include("server:validation-session-service")
findProject(":server:validation-session-service")?.name = "validation-session-service"
include("server:user-service")
findProject(":server:user-service")?.name = "user-service"
include("server:todo-service")
findProject(":server:todo-service")?.name = "todo-service"
