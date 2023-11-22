plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    //id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.paging3example"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.paging3example"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    flavorDimensions.add("version")
    productFlavors {
        create("staging") {
            applicationIdSuffix = ".stage"
            dimension = "version"
        }
        create("live") {
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //ViewModels delegation extensions for activity
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")



    // paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")

    // sdp and ssp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")


    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")


    //Android Remote Debugger
    debugImplementation("com.github.zerobranch.android-remote-debugger:debugger:1.1.2")
    releaseImplementation("com.github.zerobranch.android-remote-debugger:noop:1.1.0")

    val koinVersion = "3.4.0"
    implementation("io.insert-koin:koin-android:$koinVersion")



    val ktorVersion = "2.3.5"
    // Ktor
    implementation("io.ktor:ktor-client-core:$ktorVersion")

    // Ktor network
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-gson:$ktorVersion")
    implementation("io.ktor:ktor-serialization-gson:$ktorVersion")

    // Ktor serialization
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")

}