plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.heka.detail"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}