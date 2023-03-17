package com.proyecto404.finalProjectJP.e2e.httpApp

import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.Core.Configuration
import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryPosts
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRelationships
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.http.HttpApplication
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
            statusCode(201)
            assertThat(users.get("@alice")).isEqualTo(User("@alice", "1234"))
        }
    }

    @Test
    fun login() {
        val sessionToken: String = Given {
            users.add(User("@alice", "1234"))
            body(
                JsonObject()
                    .add("name", "@alice")
                    .add("password", "1234")
                    .toString()
            )
        } When {
            post("$baseUrl/login")
        } Then {
            statusCode(200)
        } Extract {
            path("token")
        }

        assertThat(sessionToken).isNotEmpty
        assertThat(sessionToken).isEqualTo("ecila@")
    }

    @Test
    fun post() {
        Given {
            val alice = User("@alice", "1234")
            alice.addToken(SessionToken("aToken"))
            users.add(alice)
            header("Authorization", "aToken")
            body(
                JsonObject()
                    .add("username", "@alice")
                    .add("content", "What a beautiful day!")
                    .toString()
            )
        } When {
            post("$baseUrl/posts")
        } Then {
            statusCode(201)
        }

        assertThat(posts.get(1)).isEqualTo(Post(1, "@alice", "What a beautiful day!"))
    }

    @Test
    fun read() {
        Given {
            val juan = User("@juan", "1234")
            juan.addToken(SessionToken("aToken"))
            val bob = User("@bob", "1234")
            users.add(juan)
            users.add(bob)
            posts.add(Post(1, "@bob", "What a beautiful day!"))
            posts.add(Post(2, "@bob", "Is it?"))
            header("Authorization", "aToken")
            header("Requester", "@juan")
        } When {
            get("$baseUrl/posts/@bob")
        } Then {
            statusCode(200)
            body("posts", CoreMatchers.notNullValue())
            body("posts.size()", CoreMatchers.equalTo(2))
        }
    }

    @Test
    fun follow() {
        Given {
            val juan = User("@juan", "1234")
            val bob = User("@bob", "1234")
            juan.addToken(SessionToken("aToken"))
            users.add(juan)
            users.add(bob)
            header("Authorization", "aToken")
            body(
                JsonObject()
                    .add("requester", "@juan")
                    .add("followee", "@bob")
                    .toString()
            )
        } When {
            post("$baseUrl/follow")
        } Then {
            statusCode(201)
        }

        assertThat(relationships.getFollowers("@bob")).isEqualTo(listOf("@juan"))
    }

    @BeforeEach
    fun setup() {
        httpApp.start()
    }

    @AfterEach
    fun tearDown() {
        httpApp.stop()
    }

    private val posts = InMemoryPosts()
    private val users = InMemoryUsers()
    private val relationships = InMemoryRelationships()
    private val core = Core(Configuration(users, posts, relationships))
    private val config = HttpApplication.Configuration(6060, core)
    private val httpApp = HttpApplication(config)
    private val baseUrl = "http://localhost:6060";
}
