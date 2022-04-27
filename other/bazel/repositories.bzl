load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

repositories = [
 "https://repo1.maven.org/maven2",
]

SPRING_BOOT_VERSION = "2.6.1"
SPRING_VERSION = "5.3.13"
JETBRAINS_KOTLIN_VERSION = "1.5.0"
JUNIT_JUPITER_VERSION = "5.5.0"

def rules_spring_deps():
    maven_install(
        artifacts = [
            "org.springframework.boot:spring-boot-loader:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot-starter-webflux:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot-starter-test:%s" % SPRING_BOOT_VERSION,
            "org.springframework:spring-core:%s" % SPRING_VERSION,

            "org.jetbrains.kotlin:kotlin-reflect:%s" % JETBRAINS_KOTLIN_VERSION,
            "org.jetbrains.kotlin:kotlin-stdlib:%s" % JETBRAINS_KOTLIN_VERSION,
            "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:%s" % JETBRAINS_KOTLIN_VERSION,
            "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.1",

            "com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0",

            "io.springfox:springfox-boot-starter:3.0.0",

            "org.junit.jupiter:junit-jupiter-api:%s" % JUNIT_JUPITER_VERSION,
            "org.junit.jupiter:junit-jupiter-engine:%s" % JUNIT_JUPITER_VERSION,
            "org.junit.jupiter:junit-jupiter-params:%s" % JUNIT_JUPITER_VERSION,
            "org.junit.platform:junit-platform-console:1.5.0",
            "org.junit.platform:junit-platform-suite-api:1.5.0",
        ],
        excluded_artifacts = [],
        repositories = repositories,
        fetch_sources = True,
        version_conflict_policy = "pinned",
        strict_visibility = True,
        generate_compat_repositories = False,
        maven_install_json = "//:other/bazel/maven_install.json",
        resolve_timeout = 1800,
    )
