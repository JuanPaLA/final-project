import com.nbottarini.asimov.environment.Env
import nu.studer.gradle.jooq.JooqGenerate
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Logging.WARN
import org.jooq.meta.jaxb.Matchers
import org.jooq.meta.jaxb.MatchersTableType

Env.addSearchPath(rootProject.projectDir.absolutePath)

plugins {
    kotlin("jvm") version "1.7.20"
    id("nu.studer.jooq") version "7.1.1"
    id("org.flywaydb.flyway") version "8.5.13"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

buildscript {
    dependencies { classpath("com.nbottarini:asimov-environment:2.0.0") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("junit:junit:4.13.1")
    implementation("com.eclipsesource.minimal-json:minimal-json:0.9.5")
    implementation("io.javalin:javalin:4.6.4")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("org.flywaydb:flyway-core:9.8.3")
    implementation("org.jooq:jooq:7.1.1")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("com.nbottarini:asimov-environment:2.0.0")
    jooqGenerator("org.postgresql:postgresql:42.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation(kotlin("test"))
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

flyway {
    url = "jdbc:postgresql://${Env["DB_HOST"]}:${Env["DB_PORT"]}/${Env["DB_NAME"]}"
    user = Env["DB_USER"]
    password = Env["DB_PASSWORD"]
    schemas = arrayOf("public")
    locations = arrayOf("filesystem:${project.projectDir}/src/main/resources/db")
}

jooq {
    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(Env["GENERATE_JOOQ"] == "1")
            jooqConfiguration.apply {
                logging = WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://${Env["DB_HOST"]}:${Env["DB_PORT"]}/${Env["DB_NAME"]}"
                    user = Env["DB_USER"]
                    password = Env["DB_PASSWORD"]
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    strategy.apply {
                        name = "org.jooq.codegen.DefaultGeneratorStrategy"
                        matchers = Matchers().apply {
                            tables = listOf(
                                MatchersTableType().apply {
                                    expression = ""
                                    pojoClass = org.jooq.meta.jaxb.MatcherRule().apply {
                                        expression = "\$0_dto"
                                        transform = org.jooq.meta.jaxb.MatcherTransformType.PASCAL
                                    }
                                }
                            )
                        }
                    }
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        includes = ".*"
                        excludes = ""
                        isIncludeSystemSequences = true
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isDaos = true
                        isFluentSetters = true
                        newline = "\\r\\n"
                    }
                    target.apply {
                        packageName = "org.example.final_project_jp.infrastructure.jooq.generated"
                        directory = "generated"
                    }
                }
            }
        }
    }
}

tasks.named<JooqGenerate>("generateJooq") {
    dependsOn("flywayMigrate")
    inputs.files(fileTree("src/main/resources/db"))
        .withPropertyName("migrations")
        .withPathSensitivity(PathSensitivity.RELATIVE)
    allInputsDeclared.set(true)
    outputs.cacheIf { true }
}
