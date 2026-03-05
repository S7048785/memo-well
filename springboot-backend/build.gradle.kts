plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
}

val jimmerVersion = "0.10.6"
group = "com.yyjy"
version = "0.0.1-SNAPSHOT"
description = "springboot-backend"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Sa-Token 整合 jwt
    implementation("cn.dev33:sa-token-jwt:1.44.0")
    // sa-token
    implementation("cn.dev33:sa-token-spring-boot3-starter:1.44.0")
    // kotlin-logging日志打印
    implementation("io.github.oshai:kotlin-logging-jvm:6.0.3")
    // jimmer
    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    "developmentOnly"("org.springframework.boot:spring-boot-devtools")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
    // knife4j
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.5.0")
    // spring-web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ksp {
    arg("jimmer.dto.mutable", "true")
}