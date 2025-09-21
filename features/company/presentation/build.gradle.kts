plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "io.github.monolithic.invoicer.features.company"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.components.resources)

            // Feature
            implementation(projects.features.company.services)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Voyager
            implementation(libs.bundles.voyager)
            implementation(libs.voyager.transitions)

            // Kotlin
            implementation(libs.immutable.collections)
            implementation(libs.datetime)

            // Foundation
            implementation(projects.foundation.network)
            implementation(projects.foundation.navigation)
            implementation(projects.foundation.designSystem)
            implementation(projects.foundation.ui)
            implementation(projects.foundation.utils)
            implementation(projects.foundation.session)

            // Ktor
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }

        androidUnitTest.dependencies {
            implementation(projects.foundation.testUtil)
        }
    }
}