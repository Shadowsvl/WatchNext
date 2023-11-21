import com.android.build.api.dsl.ApplicationExtension
import configuration.configureAndroidKotlin
import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.testImplementation
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
            extensions.configure(ApplicationExtension::class.java) {
                configureAndroidKotlin(this, libs)
                defaultConfig.targetSdk = libs.version("targetSDK")
            }

            dependencies {
                implementation(libs.library("androidx-lifecycle-runtimeKtx"))
                implementation(libs.library("androidx-core-splashscreen"))
                implementation(libs.library("accompanist-systemUiController"))
                testImplementation(libs.library("junit4"))
                androidTestImplementation(libs.bundle("androidx-test"))
            }
        }
    }
}