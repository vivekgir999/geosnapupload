buildscript {
    repositories {
        google() // Required for Android Gradle Plugin and Google dependencies
        mavenCentral() // Required for other dependencies
    }

    dependencies {
        // Android Gradle Plugin
        classpath(libs.gradle)

        // Google Services for Firebase and Google APIs
        classpath(libs.google.services)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}