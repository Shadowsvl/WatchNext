plugins {
    id("arch.android.library")
    id("arch.android.hilt")
}

android {
    namespace = "com.arch.database"

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.bundles.room)
    kapt(libs.room.compiler)
}