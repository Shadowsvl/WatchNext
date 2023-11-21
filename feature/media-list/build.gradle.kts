plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.heka.media_list"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}