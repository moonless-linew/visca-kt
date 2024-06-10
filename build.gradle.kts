plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)
    implementation (libs.ktor.client.core)
    implementation (libs.ktor.ktor.client.android)
    implementation(libs.ktor.network)

}