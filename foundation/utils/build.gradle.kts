plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.utils"

    buildFeatures {
        buildConfig = true
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(libs.datetime)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
    }
}