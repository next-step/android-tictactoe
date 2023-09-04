plugins {
    id("kotlin")
    id("com.google.devtools.ksp")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("com.google.dagger:hilt-core:2.47")
    ksp("com.google.dagger:hilt-android-compiler:2.47")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.4")
}
