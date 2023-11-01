// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    //KotlinSymbolProcessing
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false

    //SafeArgsNavigation
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false
}