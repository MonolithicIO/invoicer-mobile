enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.1"
}

kover {
    enableCoverage()

    reports {
        excludesAnnotatedBy = setOf(
            "io.github.monolithic.invoicer.multiplatform:foundation.utils.notations.IgnoreCoverage",
        )
    }
}

rootProject.name = "invoicer"
include(":app")
include(":multiplatform:foundation:design-system")
include(":multiplatform:features:auth")
include(":multiplatform:foundation:navigation")
include(":multiplatform:foundation:network")
include(":multiplatform:foundation:validator")
include(":multiplatform:foundation:storage")
include(":multiplatform:foundation:session")
include(":multiplatform:foundation:auth")
include(":multiplatform:features:home")
include(":multiplatform:features:invoice")
include(":multiplatform:features:customer:services")
include(":multiplatform:features:customer:presentation")
include(":multiplatform:features:qrcode-session")
include(":multiplatform:features:company:services")
include(":multiplatform:features:company:presentation")
include(":multiplatform:foundation:watchers")
include(":multiplatform:foundation:utils")
include(":multiplatform:foundation:analytics")
include(":multiplatform:sharedApp")
