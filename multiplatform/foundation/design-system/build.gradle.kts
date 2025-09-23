plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "io.github.monolithic.invoicer.foundation.designSystem"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.immutable.collections)
            api(compose.materialIconsExtended)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(compose.uiTooling)
        }

        androidUnitTest.dependencies {
            implementation(projects.multiplatform.foundation.testUtil)
        }
    }
}

compose.resources {
    publicResClass = true
}