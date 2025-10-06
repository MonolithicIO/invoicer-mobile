plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.paparazzi)

}

android {
    namespace = "io.github.monolithic.invoicer.features.customer.presentation"
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

            // Voyager
            implementation(libs.bundles.voyager)

            // Foundation
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.navigation)
            implementation(projects.multiplatform.foundation.designSystem)
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.auth)
            implementation(projects.multiplatform.foundation.watchers)

            // Kotlin
            implementation(libs.immutable.collections)

            // Features
            implementation(projects.multiplatform.features.customer.services)
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