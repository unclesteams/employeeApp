import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	war
	id("org.springframework.boot") version "2.2.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	kotlin("plugin.jpa") version "1.3.61"
}

group = "com.uncle"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	// swagger documentation
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")

	// jpa and jdbc starter
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// database
	implementation("org.postgresql:postgresql:42.2.12")

	// flyway
	implementation("org.flywaydb:flyway-core")

	// actuator and admin server for monitoring (TODO commented for now)
	//implementation("org.springframework.boot:spring-boot-starter-actuator")
	//implementation("de.codecentric:spring-boot-admin-server-ui:2.2.2")
	//implementation("de.codecentric:spring-boot-admin-server:2.2.2")
	//implementation("de.codecentric:spring-boot-admin-starter-client:2.2.2")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// jwt
	implementation("io.jsonwebtoken:jjwt:0.9.1")

	// web starter
	implementation("org.springframework.boot:spring-boot-starter-web") // default embedded container here
	 {
		 // exclude when you need to deploy the artifact to a real tomcat (few MB less)
		 // exclude(group= "org.springframework.boot", module= "spring-boot-starter-tomcat")
	 }
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
