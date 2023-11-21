package configuration

import com.android.build.api.dsl.CommonExtension
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.version
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroidKotlin(
    commonExtension: CommonExtension<*, *, *, *, *>,
    libs: VersionCatalog
) {
    commonExtension.apply {
        compileSdk = libs.version("targetSDK")

        defaultConfig {
            minSdk = libs.version("minSDK")

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_18
            targetCompatibility = JavaVersion.VERSION_18
        }

        configureKotlin()

        dependencies {
            implementation(libs.library("androidx-core-ktx"))
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure(JavaPluginExtension::class.java) {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_18.toString()
        }
    }
}