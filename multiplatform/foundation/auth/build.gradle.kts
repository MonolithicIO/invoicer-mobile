plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.auth"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.foundation)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Ktor
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.core)

            // Libs
            implementation(projects.multiplatform.foundation.utils)
            implementation(projects.multiplatform.foundation.watchers)
            implementation(projects.multiplatform.foundation.platform)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }
    }
}

dependencies {
    // Auth Providers
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}