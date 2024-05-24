plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.cineplanet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cineplanet"
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val fragment_version = "1.6.2"
    implementation("androidx.fragment:fragment:$fragment_version")
    implementation("com.squareup.picasso:picasso:2.71828")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")


    //Slider
    implementation("com.google.android.material:material:1.5.0")

    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation("me.relex:circleindicator:2.1.6")

    implementation("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")

    //dpf
    implementation ("com.dmitryborodin:pdfview-android:1.1.0")
}