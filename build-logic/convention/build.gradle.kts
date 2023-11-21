import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationArch") {
            id = "arch.android.application"
            implementationClass = "AndroidApplicationArchPlugin"
        }
        register("androidApplicationComposeArch") {
            id = "arch.android.application.compose"
            implementationClass = "AndroidApplicationComposeArchPlugin"
        }
        register("androidLibraryArch") {
            id = "arch.android.library"
            implementationClass = "AndroidLibraryArchPlugin"
        }
        register("androidLibraryComposeArch") {
            id = "arch.android.library.compose"
            implementationClass = "AndroidLibraryComposeArchPlugin"
        }
        register("androidHiltArch") {
            id = "arch.android.hilt"
            implementationClass = "AndroidHiltArchPlugin"
        }
        register("androidFeatureArch") {
            id = "arch.android.feature"
            implementationClass = "AndroidFeatureArchPlugin"
        }
        register("jvmLibraryArch") {
            id = "arch.jvm.library"
            implementationClass = "JvmLibraryArchPlugin"
        }
    }
}