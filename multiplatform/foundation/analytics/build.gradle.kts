plugins {
    id("invoicer.multiplatform.library")
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.analytics"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Libs
            implementation(projects.multiplatform.foundation.utils)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.coroutines.test)
        }

        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
        }
    }
}