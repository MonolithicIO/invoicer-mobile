plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.testUtil"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.ui)
                implementation(compose.components.resources)
            }
        }
    }
}