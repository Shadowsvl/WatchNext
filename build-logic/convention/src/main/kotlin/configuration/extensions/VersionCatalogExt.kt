package configuration.extensions

import org.gradle.api.artifacts.VersionCatalog

fun VersionCatalog.version(alias: String) = findVersion(alias).get().toString().toInt()

fun VersionCatalog.versionString(alias: String) = findVersion(alias).get().toString()

fun VersionCatalog.bundle(alias: String) = findBundle(alias).get()

fun VersionCatalog.library(alias: String) = findLibrary(alias).get()