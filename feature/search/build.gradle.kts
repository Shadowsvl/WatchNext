plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.heka.search"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}