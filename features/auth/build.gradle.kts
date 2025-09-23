plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.paparazzi)

}

android {
    namespace = "io.github.alaksion.invoicer.features.auth.presentation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.components.resources)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Feature
            implementation(projects.features.company.services)

            // Voyager
            implementation(libs.bundles.voyager)

            // Kotlin
            implementation(libs.immutable.collections)

            // Foundation
            implementation(projects.foundation.network)
            implementation(projects.foundation.navigation)
            implementation(projects.foundation.designSystem)
            implementation(projects.foundation.validator)
            implementation(projects.foundation.ui)
            implementation(projects.foundation.auth)
            implementation(projects.foundation.analytics)
            implementation(projects.foundation.utils)
            implementation(projects.foundation.watchers)
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