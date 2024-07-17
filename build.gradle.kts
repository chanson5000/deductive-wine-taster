// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(lib.plugins.android.application) apply false
    alias(lib.plugins.jetbrains.kotlin.android) apply false
    // Make sure that you have the Google services Gradle plugin 4.4.1+ dependency
    alias(lib.plugins.com.google.gms.google-services) apply false
    // Add the dependency for the Crashlytics Gradle plugin
    id(lib.plugins.com.google.firebase.crashlytics) apply false
}
    kotlin("jvm")
}
dependencies {
    implementation(kotlin-android"stdlib"))
}
repositories {
    mavenCentral()
}