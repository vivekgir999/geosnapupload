pluginManagement {
    repositories {
        google() // For Android Gradle Plugin and Google dependencies
        mavenCentral() // For other dependencies
        gradlePluginPortal() // For Gradle plugins
        maven {
            url = uri("https://repo.maven.apache.org/maven2/") // Add Maven Central explicitly
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Ensure repositories here take precedence
    repositories {
        google() // Required for Android-related dependencies
        mavenCentral() // Required for other dependencies
    }
}

rootProject.name = "geosnapupload"
include(":app")