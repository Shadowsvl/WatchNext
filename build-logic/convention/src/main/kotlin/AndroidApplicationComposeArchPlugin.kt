import com.android.build.api.dsl.ApplicationExtension
import configuration.configureAndroidCompose
import configuration.extensions.implementation
import configuration.extensions.library
import configuration.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationComposeArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                implementation(libs.library("androidx-activity-compose"))
                implementation(libs.library("androidx-navigation-compose"))
            }
        }
    }
}