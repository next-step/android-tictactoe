plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation ("com.google.dagger:hilt-core:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")
}

kapt {
    correctErrorTypes = true
}
