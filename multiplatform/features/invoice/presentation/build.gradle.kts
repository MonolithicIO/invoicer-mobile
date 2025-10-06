plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "io.github.monolithic.invoicer.features.invoice.presentation"
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
            implementation(libs.voyager.transitions)

            // Kotlin
            implementation(libs.immutable.collections)
            implementation(libs.datetime)

            // Feature
            implementation(projects.multiplatform.features.customer.services)
            implementation(projects.multiplatform.features.invoice.services)

            // Foundation
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.navigation)
            implementation(projects.multiplatform.foundation.designSystem)
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.watchers)
            implementation(projects.multiplatform.foundation.auth)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }

        androidUnitTest.dependencies {
            implementation(projects.multiplatform.foundation.utils)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(compose.uiTooling)
        }
    }
}