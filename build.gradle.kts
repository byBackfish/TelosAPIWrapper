import org.gradle.kotlin.dsl.from

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"

    id("maven-publish")
}

group = "de.bybackfish.telosapi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0-RC")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "telosapiwrapper"

            from(components["java"])
        }
    }
}