import org.danilopianini.gradle.mavencentral.mavenCentral

plugins {
    kotlin("jvm")
    jacoco
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.danilopianini.git-sensitive-semantic-versioning")
    id("org.danilopianini.publish-on-central")
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

kotlin {
    target {
        compilations.all {
            kotlinOptions {
                allWarningsAsErrors = true
                freeCompilerArgs = listOf("-XXLanguage:+InlineClasses", "-Xopt-in=kotlin.RequiresOptIn")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.jacocoTestReport {
    reports {
        // Used by Codecov.io
        xml.isEnabled = true
    }
}

detekt {
    failFast = true
    buildUponDefaultConfig = true
    config = files("$projectDir/config/detekt/detekt.yml")
    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = true
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
}

publishOnCentral {
    projectLongName = "Template Kotlin JVM Project"
    projectDescription = "A template repository for Kotlin JVM projects"
    repository("https://maven.pkg.github.com/danysk/${rootProject.name}".toLowerCase()) {
        user = "DanySK"
        password = System.getenv("GITHUB_TOKEN")
    }
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                developers {
                    developer {
                        name.set("Danilo Pianini")
                        email.set("danilo.pianini@gmail.com")
                        url.set("http://www.danilopianini.org/")
                    }
                }
            }
        }
    }
}
