import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val sqldelight_version: String by project
val kotlinx_datetime_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.9"
    id("app.cash.sqldelight") version "2.0.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

group = "com.ring.ring"
version = "0.0.1"

application {
    mainClass.set("com.ring.ring.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinx_datetime_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("app.cash.sqldelight:sqlite-driver:$sqldelight_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

sqldelight {
    databases {
        create("LocalDb") {
            packageName.set("data.local.db")
            srcDirs("src/main/kotlin/com/ring/ring/")
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$projectDir/config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")
    ignoreFailures = true
    parallel = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        md.required.set(true) // simple Markdown format
    }
}

//val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
//    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.md")) // or "reports/detekt/merge.sarif"
//}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}