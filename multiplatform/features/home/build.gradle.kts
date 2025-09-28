plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
}

android {
    namespace = "io.github.monolithic.features.home.presentation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.components.resources)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Voyager
            implementation(libs.bundles.voyager)
            implementation(libs.voyager.tabs)

            // Kotlin
            implementation(libs.immutable.collections)
            implementation(libs.datetime)

            // Feature
            implementation(projects.multiplatform.features.invoice.services)

            // Foundation
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.navigation)
            implementation(projects.multiplatform.foundation.designSystem)
            implementation(projects.multiplatform.foundation.auth)
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.watchers)
        }
    }
}