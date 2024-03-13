package configuration

import com.android.build.api.dsl.CommonExtension
import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.debugImplementation
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.versionString
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.versionString("kotlinComposeCompiler")
        }

        dependencies {
            val bom = libs.library("androidx-compose-bom")
            implementation(platform(bom))
            implementation(libs.bundle("compose"))
            implementation(libs.bundle("lifecycle-compose"))
            debugImplementation(libs.bundle("compose-debug"))
            androidTestImplementation(platform(bom))
        }
    }
}