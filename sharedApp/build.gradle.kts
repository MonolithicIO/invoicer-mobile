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
            implementation(projects.foundation.navigation)
            implementation(projects.foundation.designSystem)
            implementation(projects.foundation.network)
            implementation(projects.foundation.validator)
            implementation(projects.foundation.watchers)
            implementation(projects.foundation.network)
            implementation(projects.foundation.utils)
            implementation(projects.foundation.session)

            // Features
            implementation(projects.features.auth.presentation)
            implementation(projects.features.home)
            implementation(projects.features.invoice)
            implementation(projects.features.qrcodeSession)
            implementation(projects.features.company.presentation)
            implementation(projects.features.company.services)
            implementation(projects.features.customer.services)
            implementation(projects.features.customer.presentation)

            // Exported
            api(projects.foundation.analytics)
            api(projects.foundation.storage)
            api(projects.foundation.auth)

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
            export(projects.foundation.storage)
            export(projects.foundation.auth)
            export(projects.foundation.analytics)
        }
    }
}
