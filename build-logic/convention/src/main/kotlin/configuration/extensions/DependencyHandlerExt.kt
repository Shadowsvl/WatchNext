package configuration.extensions

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: Any) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: Any) {
    add("kapt", dependency)
}