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
            "io.github.monolithic.invoicer.foundation.utils.notations.IgnoreCoverage",
        )
    }
}

rootProject.name = "invoicer"
include(":app")
include(":foundation:design-system")
include(":features:auth")
include(":foundation:navigation")
include(":foundation:network")
include(":foundation:validator")
include(":foundation:storage")
include(":foundation:session")
include(":foundation:auth")
include(":features:home")
include(":foundation:ui")
include(":features:invoice")
include(":features:customer:services")
include(":features:customer:presentation")
include(":features:qrcode-session")
include(":features:company:services")
include(":features:company:presentation")
include(":foundation:watchers")
include(":foundation:utils")
include(":foundation:test-util")
include(":foundation:analytics")
include(":sharedApp")
