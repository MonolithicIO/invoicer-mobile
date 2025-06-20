package buildLogic.configs

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object AppConfig {
    const val compileSdk = 35
    const val minSdk = 29
    const val targetSdk = 35
    const val versionCode = 1
    const val versionName = "1.0"
    const val appId = "io.github.alaksion.invoicer"

    val javaVersion = JavaVersion.VERSION_11
    val jvmTargetMp = JvmTarget.JVM_11
    const val jvmTarget = "11"
}