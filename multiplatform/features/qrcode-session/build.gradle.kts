plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.monolithic.invoicer.features.qrcodeSession"
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

            // Ktor
            implementation(libs.ktor.client.core)

            // Kotlin
            implementation(libs.datetime)
            implementation(libs.coroutines.core)

            // Foundation
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.navigation)
            implementation(projects.multiplatform.foundation.designSystem)
            implementation(projects.multiplatform.foundation.utils)

            // Voyager
            implementation(libs.bundles.voyager)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }

        androidMain.dependencies {
            // Camera
            implementation(libs.bundles.camerax)
            implementation(libs.google.mlkit)
        }
    }
}