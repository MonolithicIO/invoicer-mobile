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
            implementation(projects.multiplatform.features.company.services)

            // Voyager
            implementation(libs.bundles.voyager)

            // Kotlin
            implementation(libs.immutable.collections)

            // Foundation
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.navigation)
            implementation(projects.multiplatform.foundation.designSystem)
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.auth)
            implementation(projects.multiplatform.foundation.analytics)
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.watchers)
            implementation(projects.multiplatform.foundation.platform)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }

        androidUnitTest.dependencies {
            implementation(projects.multiplatform.foundation.utils)
        }
    }
}