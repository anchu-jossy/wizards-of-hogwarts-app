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
//    packaging {
//
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//            excludes += "/META-INF/gradle/incremental.annotation.processors"
//            excludes += "/META-INF/AL2.0"
//            excludes += "META-INF/LGPL2.1"
//            excludes += "META-INF/NOTICE"
//        }
//    }

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

        // Debug and testing dependencies
        debugImplementation(libs.hilt.android.testing)
        debugImplementation(libs.kotlinx.coroutines.test)
        kaptTest(libs.hilt.android.compiler)

        androidTestImplementation(libs.mockk.android) // For Android Instrumentation tests
        testImplementation(libs.mockk) // For Unit tests
        testImplementation(libs.mockito.kotlin) // Mockito for unit tests
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
        testImplementation(libs.junit)
        implementation(libs.navigation.compose)
        testImplementation(libs.hilt.android.compiler)
        kaptAndroidTest(libs.hilt.android.compiler)

        // Dependency injection with Hilt
        implementation(libs.hilt.android)
        kapt(libs.hilt.android.compiler)
        implementation(libs.hilt.navigation.compose)



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
