plugins {
    id("arch.android.application")
    id("arch.android.application.compose")
    id("arch.android.hilt")
}

android {
    namespace = "com.heka.watchnext"

    defaultConfig {
        applicationId = "com.heka.watchnext"
        versionCode = 5
        versionName = "1.2.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:design-system"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))

    implementation(libs.coil.compose)

    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:media-list"))
    implementation(project(":feature:search"))
}