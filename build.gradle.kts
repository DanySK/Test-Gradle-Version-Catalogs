plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.detekt)
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
