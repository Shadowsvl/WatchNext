import configuration.extensions.implementation
import configuration.extensions.kapt
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                implementation(libs.library("hilt-android"))
                kapt(libs.library("hilt-compiler"))
                implementation(libs.library("androidx-hilt-navigation-compose"))
            }
        }
    }
}