plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")

    // Junit
    testImplementation("junit:junit:${Version.junit4}")
    testImplementation("com.google.truth:truth:${Version.truth}")

    // Mockk
    testImplementation("io.mockk:mockk:${Version.mockk}")
}