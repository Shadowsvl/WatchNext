import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import configuration.configureAndroidKotlin
import configuration.disableUnnecessaryAndroidTests
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.testImplementation
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
            extensions.configure(LibraryExtension::class.java) {
                configureAndroidKotlin(this, libs)
                defaultConfig.targetSdk = libs.version("targetSDK")
            }
            extensions.configure(LibraryAndroidComponentsExtension::class.java) {
                disableUnnecessaryAndroidTests(target)
            }

            dependencies {
                implementation(libs.library("kotlinx-coroutines-android"))
                testImplementation(libs.library("junit4"))
            }
        }
    }
}