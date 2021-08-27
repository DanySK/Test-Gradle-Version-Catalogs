enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    plugins {
        kotlin("jvm") version "1.5.30"
        id("io.gitlab.arturbosch.detekt") version "1.18.0"
    }
}

rootProject.name = "Test-Gradle-Version-Catalogs"
