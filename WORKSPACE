workspace(name = "qtrade")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")


# Maven
RULES_JVM_EXTERNAL_TAG = "4.2"
RULES_JVM_EXTERNAL_SHA = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)


# Spring Boot
http_archive(
    name = "rules_spring",
    sha256 = "9385652bb92d365675d1ca7c963672a8091dc5940a9e307104d3c92e7a789c8e",
    urls = [
         "https://github.com/salesforce/rules_spring/releases/download/2.1.4/rules-spring-2.1.4.zip",
    ],
)

load("//:repositories.bzl", "rules_spring_deps")
rules_spring_deps()

load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()


# Kotlin
rules_kotlin_version = "v1.5.0-beta-4"
rules_kotlin_sha = "6cbd4e5768bdfae1598662e40272729ec9ece8b7bded8f0d2c81c8ff96dc139d"
http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = rules_kotlin_sha,
    type = "tgz",
    urls = [
        "https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % rules_kotlin_version
    ],
)

KOTLIN_VERSION = "1.4.20"
KOTLINC_RELEASE_SHA = "11db93a4d6789e3406c7f60b9f267eba26d6483dcd771eff9f85bb7e9837011f"
KOTLINC_RELEASE = {
    "urls": [
        "https://github.com/JetBrains/kotlin/releases/download/v{v}/kotlin-compiler-{v}.zip".format(v = KOTLIN_VERSION),
    ],
    "sha256": KOTLINC_RELEASE_SHA,
}
load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")
kotlin_repositories()

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")
kt_register_toolchains()
#register_toolchains("//common/kotlin:kotlin_toolchain")


#load("@rules_jvm_external//:defs.bzl", "maven_install")

#maven_install(
#    artifacts = [
#        "org.springframework.boot:spring-boot-starter-webflux:2.6.1",
#        "org.springframework.boot:spring-boot-loader-tools:2.6.1",
#        "com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0",
#        "io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5",
#        "org.jetbrains.kotlin:kotlin-reflect:1.5.32",
#        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.32",
#        "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2",
#        "io.springfox:springfox-boot-starter:3.0.0",
#        "org.springframework.boot:spring-boot-starter-test:2.6.1",
#        "io.projectreactor:reactor-test:3.4.12",
#    ],
#    repositories = [
#        "https://maven.google.com",
#        "https://repo1.maven.org/maven2",
#    ],
#    maven_install_json = "@//:maven_install.json",
#)
