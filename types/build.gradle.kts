plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
}

group = "gg.skytils.hypixel.types"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    testImplementation("io.ktor:ktor-client-core:2.3.7")
    testImplementation("io.ktor:ktor-client-cio:2.3.7")
    testImplementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.7") {
        exclude(group = "org.jetbrains.kotlinx")
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}