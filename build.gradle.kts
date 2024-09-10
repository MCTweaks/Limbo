plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.0.0" // Shadow for creating uber JARs
}

group = "com.loohp"
version = "0.7.10-ALPHA"

description = "Standalone Limbo Minecraft Server"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("com.github.Querz:NBT:5.5")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.yaml:snakeyaml:1.26")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("net.md-5:bungeecord-chat:1.18-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-text-serializer-gson:4.17.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.17.0")
    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")
    implementation("net.kyori:adventure-api:4.17.0-SNAPSHOT")
    implementation("net.kyori:adventure-nbt:4.17.0-SNAPSHOT")
    implementation("org.fusesource.jansi:jansi:1.18")
    implementation("org.jline:jline:3.16.0")
    implementation("jline:jline:2.11")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "com.loohp.limbo.Limbo",
                "Limbo-Version" to project.version
        )
    }
    archiveBaseName.set("limbo")
    archiveVersion.set("1.21.1")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

tasks.shadowJar {
    archiveClassifier.set("")
    mergeServiceFiles()
}

tasks.named("build") {
    dependsOn(tasks.named("shadowJar"))
}
