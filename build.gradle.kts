plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.30-RC"
    id("application")
    id("org.flywaydb.flyway") version "7.5.3"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:29.0-jre")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation(platform("org.http4k:http4k-bom:4.0.0.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-apache")
    implementation("org.http4k:http4k-graphql:4.0.0.0")
    implementation("com.expediagroup:graphql-kotlin-server:4.0.0-alpha.13")

    implementation("org.xerial", "sqlite-jdbc", "3.23.1")
    implementation("org.jetbrains.exposed:exposed-core:0.28.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.28.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.28.1")

    implementation("org.flywaydb", "flyway-core", "7.5.3")

    implementation("org.valiktor:valiktor-core:0.12.0")
}

application {
    mainClass.set("poc.AppKt")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

flyway {
    url = "jdbc:sqlite:data.db"
    user = ""
    password = ""
}
