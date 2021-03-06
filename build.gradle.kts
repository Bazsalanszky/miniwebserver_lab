val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.palantir.docker") version "0.28.0"
}

group = "eu.toldi"
version = "0.0.1"
application {
    mainClass.set("eu.toldi.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}
tasks {
    shadowJar {
        archiveBaseName.set("miniweb")
        archiveClassifier.set("")
        archiveVersion.set("")
    }
    docker {
        name = "${project.name}:${project.version}"
        files("./build/libs/miniweb.jar")
    }
}

