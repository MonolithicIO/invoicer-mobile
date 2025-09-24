plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
}

android {
    namespace = "io.github.monolithic.invoicer.sharedApp"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.animationGraphics)
            implementation(compose.foundation)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            // Foundation
            implementation(projects.multiplatform.foundation.navigation)
            implementation(projects.multiplatform.foundation.designSystem)
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.watchers)
            implementation(projects.multiplatform.foundation.network)
            implementation(projects.multiplatform.foundation.utils)

            // Features
            implementation(projects.multiplatform.features.auth)
            implementation(projects.multiplatform.features.home)
            implementation(projects.multiplatform.features.invoice)
            implementation(projects.multiplatform.features.qrcodeSession)
            implementation(projects.multiplatform.features.company.presentation)
            implementation(projects.multiplatform.features.company.services)
            implementation(projects.multiplatform.features.customer.services)
            implementation(projects.multiplatform.features.customer.presentation)
            implementation(projects.multiplatform.foundation.analytics)
            implementation(projects.multiplatform.foundation.storage)

            // Exported
            api(projects.multiplatform.foundation.platform)
            api(projects.multiplatform.foundation.auth)

            // Voyager
            implementation(libs.bundles.voyager)
            implementation(libs.voyager.transitions)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "invoicerShared"
            isStatic = true
            export(projects.multiplatform.foundation.platform)
            export(projects.multiplatform.foundation.auth)
        }
    }
}
