plugins {
    id("invoicer.multiplatform.library")
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.watchers"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.multiplatform.foundation.utils)
            implementation(libs.coroutines.core)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
    }
}
