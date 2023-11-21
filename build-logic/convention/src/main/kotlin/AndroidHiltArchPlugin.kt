import configuration.extensions.implementation
import configuration.extensions.kapt
import configuration.extensions.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
            }

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
            dependencies {
                implementation(libs.library("hilt-android"))
                kapt(libs.library("hilt-compiler"))
                implementation(libs.library("androidx-hilt-navigation-compose"))
            }
        }
    }
}