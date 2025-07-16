plugins {
    id("invoicer.multiplatform.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.session"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
    }
}