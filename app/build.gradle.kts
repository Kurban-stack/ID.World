plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.idworld"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.idworld"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "operators"
    productFlavors {
        create("operatorA") {
            dimension = "operators"
            applicationId = "com.example.idworld.operatorA"
        }
        create("operatorB") {
            dimension = "operators"
            applicationIdSuffix = ".operatorB"
        }
        create("operatorC") {
            dimension = "operators"
            applicationIdSuffix = ".operatorC"
        }
        create("operatorD") {
            dimension = "operators"
            applicationIdSuffix = ".operatorD"
        }
        create("operatorE") {
            dimension = "operators"
            applicationIdSuffix = ".operatorE"
        }
        create("operatorF") {
            dimension = "operators"
            applicationIdSuffix = ".operatorF"
        }
        create("operatorG") {
            dimension = "operators"
            applicationIdSuffix = ".operatorG"
        }
        create("operatorH") {
            dimension = "operators"
            applicationIdSuffix = ".operatorH"
        }
        create("operatorI") {
            dimension = "operators"
            applicationIdSuffix = ".operatorI"
        }
        create("operatorJ") {
            dimension = "operators"
            applicationIdSuffix = ".operatorJ"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}