plugins {
    id("arch.android.application")
    id("arch.android.application.compose")
    id("arch.android.hilt")
}

android {
    namespace = "com.heka.watchnext"

    defaultConfig {
        applicationId = "com.heka.watchnext"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

}