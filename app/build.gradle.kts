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
        implementation(libs.navigation.fragment)
        implementation(libs.navigation.ui)
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

        //Firebase
        implementation("com.google.firebase:firebase-analytics")
        implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
        //
        implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
        implementation("com.google.firebase:firebase-database")
        //
        implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
        implementation("com.google.firebase:firebase-storage")
        //firebase auth:
        // Import the BoM for the Firebase platform
        implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
        implementation("com.google.firebase:firebase-auth")
        implementation ("com.google.firebase:firebase-core:20.0.0")

        /*Base*/
        implementation("androidx.room:room-runtime:2.5.0")
        implementation ("androidx.work:work-runtime:2.8.0")
        annotationProcessor ("androidx.room:room-compiler:2.5.0")
        /*gOOGLES*/
        implementation("com.google.android.gms:play-services-maps:18.2.0")
        implementation("com.google.android.gms:play-services-location:21.0.1")
    }

    apply(plugin = "com.google.gms.google-services")