package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryPosts
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRelationships
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Wall.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

class WallTest {
    @Test
    fun `wall brings user's subscription timeline posts`() {
        posts.add(Post(0, "@bob", "Damn! We lost!"))
        posts.add(Post(1, "@bob", "Good game though."))
        relationships.add(Relationship(alice, bob))

        val response = wall.exec(Request("@alice", aToken))

        assertThat(response.timeline).isEqualTo(listOfPosts)
    }

    @Test
    fun `user must be authenticated to see their timeline wall`() {
        val authService = AuthService()

        val wall = Wall(posts, users, relationships, authService)

        assertThrows<UserNotAuthenticatedError> {
            wall.exec(Request("@alice", aToken))
        }
    }

    @BeforeEach
    fun setup() {
        users.add(juan)
        users.add(bob)
        users.add(alice)
        every { authService.validateToken(any(), any()) } returns true
    }

    private val alice = User("@alice", "1234")
    private val bob = User("@bob", "1234")
    private val juan = User("@juan", "1234")
    private val posts = InMemoryPosts()
    private val users = InMemoryUsers()
    private val relationships = InMemoryRelationships()
    private val authService = mockk<AuthService>(relaxed = true)
    private val wall = Wall(posts, users, relationships, authService)
    private val aToken = SessionToken("aToken")
    private val listOfPosts = listOf(
        Post(0, "@bob", "Damn! We lost!"),
        Post(1, "@bob", "Good game though.")
    )
}
