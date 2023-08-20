plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "io.ecosed.plugin"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>(name = "release") {
                from(components["release"])
            }
        }
    }
}

dependencies {
    implementation(
        dependencyNotation = "org.lsposed.hiddenapibypass:hiddenapibypass:4.3"
    )
}