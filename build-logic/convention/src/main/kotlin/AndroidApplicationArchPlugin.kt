import com.android.build.api.dsl.ApplicationExtension
import configuration.configureAndroidKotlin
import configuration.extensions.androidTestImplementation
import configuration.extensions.bundle
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import configuration.extensions.testImplementation
import configuration.extensions.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidKotlin(this)
                defaultConfig.targetSdk = libs.version("targetSDK")
            }

            dependencies {
                implementation(libs.library("androidx-lifecycle-runtime-ktx"))
                implementation(libs.library("androidx-core-splashscreen"))
                implementation(libs.library("accompanist-systemuicontroller"))
                testImplementation(libs.library("junit"))
                androidTestImplementation(libs.bundle("androidx-test"))
            }
        }
    }
}