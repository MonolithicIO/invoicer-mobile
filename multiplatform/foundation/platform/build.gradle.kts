import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    id("invoicer.multiplatform.library")
    id("invoicer.compose")
    alias(libs.plugins.buildKonfig)
}

val properties = Properties()
properties.load(rootProject.file("local.properties").inputStream())

android {
    namespace = "io.github.monolithic.invoicer.foundation.platform"
}

buildkonfig {
    packageName = "io.github.monolithic.invoicer.foundation.platform"
    objectName = "PlatformBuildConfig"
    defaultConfigs {
        buildConfigField(
            type = FieldSpec.Type.STRING,
            value = properties.getProperty("FIREBASE_WEB_ID"),
            name = "FIREBASE_WEB_ID"
        )
    }
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
            implementation(libs.koin.android)
            implementation(libs.firebase.auth)
            implementation(libs.bundles.identity)
            implementation(libs.google.services.auth)
            implementation(libs.androidx.activity.compose)
        }

        commonMain.dependencies {
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(projects.multiplatform.foundation.utils)
            implementation(compose.runtime)
        }
    }
}