plugins {
    id("invoicer.multiplatform.library")
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.platform"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
        }

        commonMain.dependencies {
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
    }
}