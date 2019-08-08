import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    kotlin("kapt") version "1.3.31"
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.10.0"
    maven
}

group = "flavor.pie"
version = "0.1.1"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("kludge") {
            id = "flavor.pie.kludge"
            displayName = "Kludge Compiler Gradle Plugin"
            description = "A Gradle plugin to load the Kludge compiler plugin (for additional functionality for the Kludge library)."
            implementationClass = "flavor.pie.kludgec.KludgeGradlePlugin"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("gradle-plugin-api"))
    val autoService = create("com.google.auto.service:auto-service:1.0-rc4")
    compileOnly(autoService)
    kapt(autoService)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

pluginBundle {
    website = "https://github.com/pie-flavor/KludgeGradle/"
    vcsUrl = "https://github.com/pie-flavor/KludgeGradle"
    tags = listOf("kludge", "sponge", "kotlin", "minecraft", "plugin")
}
