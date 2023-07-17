plugins {
    application
    kotlin("jvm") version "1.8.0"
    id("maven-publish")
}

group = "com.delta"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.8.9")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("myLibrary") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "DeltaTilousLogic"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}