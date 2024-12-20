plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "mx.uacj.aplicacionfragmentos"
    compileSdk = 34

    defaultConfig {
        applicationId = "mx.uacj.aplicacionfragmentos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Librerias para usar el fragment view en android
    val version_fragment = "1.8.4"
    implementation("androidx.fragment:fragment-ktx:${version_fragment}")

    //Librerias para usar el Navigation Graph
    val version_navigation_graph = "2.8.3"
    implementation("androidx.navigation:navigation-fragment-ktx:${version_navigation_graph}")
    implementation("androidx.navigation:navigation-ui-ktx:${version_navigation_graph}")
}