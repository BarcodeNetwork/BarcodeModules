plugins {
    id("barcode.project-manager")
}

group = "com.vjh0107.barcode"
version = "1.0.0"

subprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}