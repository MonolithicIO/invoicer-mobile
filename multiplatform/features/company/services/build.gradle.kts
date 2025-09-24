plugins {
    id("invoicer.multiplatform.library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "io.github.monolithic.invoicer.features.company"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Kotlin
            implementation(libs.immutable.collections)
            implementation(libs.datetime)

            // Foundation
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.platform)

            // Ktor
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }
    }
}