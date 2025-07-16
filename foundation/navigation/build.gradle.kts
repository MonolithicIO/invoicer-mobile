plugins {
    id("invoicer.multiplatform.library")
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.navigation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.voyager.navigator)
        }
    }
}
