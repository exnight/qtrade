load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

repositories = [
 "https://repo1.maven.org/maven2",
]

SPRING_BOOT_VERSION = "2.4.4"
SPRING_VERSION = "5.3.5"

def rules_spring_deps():
    maven_install(
        artifacts = [
            "org.springframework.boot:spring-boot-loader:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot-starter-webflux:%s" % SPRING_BOOT_VERSION,
            "org.springframework.boot:spring-boot-starter-test:%s" % SPRING_BOOT_VERSION,

            "org.springframework:spring-beans:%s" % SPRING_VERSION,
#            "org.springframework:spring-context:%s" % SPRING_VERSION,
#            "org.springframework:spring-context-support:%s" % SPRING_VERSION,
#            "org.springframework:spring-core:%s" % SPRING_VERSION,
#            "org.springframework:spring-expression:%s" % SPRING_VERSION,
            "org.springframework:spring-web:%s" % SPRING_VERSION,

            "org.jetbrains.kotlin:kotlin-reflect:1.4.20",
            "org.jetbrains.kotlin:kotlin-stdlib-common:1.4.20",
#            "org.jetbrains.kotlin:kotlin-test-junit:1.4.20",
#            "org.jetbrains.kotlin:kotlin-test:1.4.20",
            "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.4.1",

            "com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0",

            "io.springfox:springfox-boot-starter:3.0.0",
        ],
        excluded_artifacts = [],
        repositories = repositories,
        fetch_sources = True,
        version_conflict_policy = "pinned",
        strict_visibility = True,
        generate_compat_repositories = False,
        maven_install_json = "//:maven_install.json",
        resolve_timeout = 1800,
    )
