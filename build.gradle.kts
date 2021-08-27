plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
}

group = "org.danilopianini"

repositories {
    mavenCentral()
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    implementation(kotlin("stdlib"))
    testImplementation(libs.bundles.kotest)
    testImplementation(libs.mockito)
}
