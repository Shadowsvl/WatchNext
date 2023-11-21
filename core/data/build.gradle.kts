plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.arch.data"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
}