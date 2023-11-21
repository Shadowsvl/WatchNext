import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

val properties = Properties()
properties.load(project.rootProject.file("secret.properties").reader())
val apiKey: String = properties.getProperty("API_KEY")

android {
    namespace = "com.arch.network"

    defaultConfig {
        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_IMAGES_BASE_URL", "\"https://image.tmdb.org/t/p/\"")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.bundles.retrofit)
}