import com.android.build.api.dsl.LibraryExtension
import configuration.configureAndroidCompose
import configuration.extensions.implementation
import configuration.extensions.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeArchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            val libs = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
            extensions.configure(LibraryExtension::class.java) {
                configureAndroidCompose(this, libs)
            }

            dependencies {
                implementation(libs.library("androidx-navigation-compose"))
            }
        }
    }
}