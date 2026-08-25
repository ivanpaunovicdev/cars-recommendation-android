import java.util.Properties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}



val localPropertiesFile = rootProject.file("local.properties")

require(localPropertiesFile.exists()) {
    """
    Missing local.properties file.

    Please create local.properties in the project root and add:

    DEBUG_BASE_URL=http://192.168.0.22:8080/
    RELEASE_BASE_URL=https://example.com/
    """.trimIndent()
}

val localProperties = Properties().apply {
    localPropertiesFile.inputStream().use(::load)
}

val debugBaseUrl = localProperties.getProperty("DEBUG_BASE_URL")
    ?: error(
        """
        DEBUG_BASE_URL is missing from local.properties.

        Example:
        DEBUG_BASE_URL=http://192.168.0.22:8080/
        """.trimIndent()
    )

val releaseBaseUrl = localProperties.getProperty("RELEASE_BASE_URL")
    ?: error(
        """
        RELEASE_BASE_URL is missing from local.properties.

        Example:
        RELEASE_BASE_URL=https://example.com/
        """.trimIndent()
    )

android {
    namespace = "com.example.carsrecommendationapp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.carsrecommendationapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"$debugBaseUrl\""
            )
        }

        release {
            isMinifyEnabled = true

            buildConfigField(
                "String",
                "BASE_URL",
                "\"$releaseBaseUrl\""
            )

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}