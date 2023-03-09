package com.proyecto404.finalProjectJP.e2e.httpApp

import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.Core.Configuration
import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.http.HttpApplication
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


class TwitterE2ETest {
    @Test
    fun signup() {
        Given {
            body(
                JsonObject()
                    .add("name", "@alice")
                    .add("password", "1234")
                    .toString()
            )
        } When {
            post("$baseUrl/users")
        } Then {
            statusCode(200)
            assertThat(users.get("@alice")).isEqualTo(User("@alice", "1234"))
        }
    }

    @BeforeEach
    fun setup() {
        httpApp.start()
    }

    @AfterEach
    fun tearDown() {
        httpApp.stop()
    }

    private val users = InMemoryUsers()
    private val core = Core(Configuration(users))
    private val config = HttpApplication.Configuration(6060, core)
    private val httpApp = HttpApplication(config)
    private val baseUrl = "http://localhost:6060";
}
