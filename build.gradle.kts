import org.gradle.internal.os.OperatingSystem

plugins {
    java
    `java-library`
    `maven-publish`
    signing
}



group = "org.kakara"
version = "1.0-SNAPSHOT"
val artifactName = "lava"
java {
    targetCompatibility = JavaVersion.VERSION_11
    sourceCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven("https://repo.ryandw11.com/repository/maven-releases")
    jcenter()
}

publishing {
    publications {

        create<MavenPublication>("mavenJava") {
            artifact(tasks["jar"])

            artifactId = artifactName
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(artifactName)
            }
        }
    }
    repositories {
        maven {

            val releasesRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            val snapshotsRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials(PasswordCredentials::class)

        }
        mavenLocal()
    }
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}



dependencies {

}