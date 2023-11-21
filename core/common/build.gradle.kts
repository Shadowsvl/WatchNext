plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.arch.common"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}