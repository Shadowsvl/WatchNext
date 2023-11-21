pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
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

rootProject.name = "Watch Next"
include(":app")
include(":core:common")
include(":core:design-system")
include(":core:model")
include(":core:ui")
include(":core:network")
include(":core:database")
include(":core:data")
include(":feature:home")