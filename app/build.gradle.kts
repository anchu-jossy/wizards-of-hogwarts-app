plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.wizardsofhogwarts"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wizardsofhogwarts"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.example.wizardsofhogwarts.HiltTestRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://hp-api.onrender.com/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://hp-api.onrender.com/\"")
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

    buildFeatures {
        compose = true
        buildConfig = true // Ensure buildConfig is enabled to generate the BuildConfig.java
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources.excludes.addAll(
            listOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",

        )
        )
    }


    dependencies {
        // Core AndroidX libraries
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)

        // Jetpack Compose libraries
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)

        // Core and utility libraries
        implementation(libs.core)
        implementation(libs.gson)
        implementation(libs.multidex)
        implementation(libs.androidx.ui.test.junit4.android)

        // Dependency injection with Hilt
        implementation(libs.hilt.android)
        kapt(libs.hilt.android.compiler)
        implementation(libs.hilt.navigation.compose)


        // Unit Testing dependencies
        testImplementation(libs.mockk) // MockK for mocking in Unit tests
        testImplementation(libs.mockito.kotlin) // Mockito for mocking in Unit tests
        testImplementation(libs.junit) // JUnit testing framework for unit tests
        testImplementation(libs.hilt.android.compiler) // Hilt compiler for unit tests, required for generating Dagger components

        // Instrumentation Testing dependencies
        androidTestImplementation(libs.mockk.android) // MockK for mocking in Android Instrumentation tests
        androidTestImplementation(libs.androidx.ui.test.junit4) // JUnit4 for UI tests with Jetpack Compose
        debugImplementation(libs.androidx.ui.tooling) // Tooling support for Compose, including the ability to preview Compose UI elements in Android Studio
        debugImplementation(libs.androidx.ui.test.manifest) // Allows testing UI elements in Compose within Android Instrumentation tests
        kaptAndroidTest(libs.hilt.android.compiler) // Hilt compiler for Android Instrumentation tests, required for generating Dagger components
        androidTestImplementation ("androidx.test.ext:junit:1.1.5") // JUnit extension for AndroidX
        androidTestImplementation ("androidx.test:runner:1.5.2") // AndroidJUnitRunner
        androidTestImplementation ("androidx.test:rules:1.5.2") // Android test rules

        // Other dependencies
        implementation(libs.navigation.compose) // Jetpack Compose Navigation component




        // Networking with Retrofit
        implementation(libs.retrofit)
        implementation(libs.retrofit.converter)
        implementation(libs.logging.interceptor)

        // Paging with Room support
        implementation(libs.paging)
        implementation(libs.paging.compose)

        // Image loading with Coil
        implementation(libs.coil.compose)

        // Room database
        implementation(libs.room.runtime)
        implementation(libs.room.ktx)
        kapt(libs.room.compiler)
        implementation(libs.room.paging)

        // Datastore for preferences
        implementation(libs.androidx.datastore.preferences)
        implementation(kotlin("test"))
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}
dependencies {
    implementation(libs.androidx.runner)
}
