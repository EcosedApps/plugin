plugins {
    id(id = "com.android.library")
    id(id = "org.jetbrains.kotlin.android")
    id(id = "maven-publish")
}

android {
    namespace = "io.ecosed.plugin"
    compileSdk = 34
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
    implementation(dependencyNotation = "androidx.annotation:annotation:1.7.0")
    implementation(dependencyNotation = "androidx.fragment:fragment-ktx:1.6.1")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-common:2.6.2")
}