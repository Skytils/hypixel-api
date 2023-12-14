plugins {
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
}

group = "gg.skytils.hypixel.types"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}