pluginManagement {
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
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https.maven.pkg.jetbrains.space/public/p/compose/dev") // Added for JetBrains Compose
        maven("https://maven.kotlin.org/daemon")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        maven("https.maven.pkg.jetbrains.space/kotlin/p/kotlin/native") // Added for Kotlin Native
        maven("https.maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
        mavenLocal() // Added as a potential fix for the 'ivy' repo issue
    }
}

rootProject.name = "Unsplash Image Caching Library"
include(":app")
