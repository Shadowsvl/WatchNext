plugins {
    id("arch.android.feature")
}

android {
    namespace = "com.heka.home"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(libs.accompanist.swiperefresh)
}