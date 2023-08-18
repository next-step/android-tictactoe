plugins {
	id("java-library")
	id("org.jetbrains.kotlin.jvm")
	id("kotlin")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
	testImplementation("junit:junit:4.13.2")
	testImplementation("com.google.truth:truth:1.1.3")
}