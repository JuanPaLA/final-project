package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRelationships
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Following.Request
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FellowshipsTest {
    @Test
    fun `following returns users followed`() {
        relationships.add(Relationship(alice, bob))
        relationships.add(Relationship(alice, juan))

        val response = following.exec(Request("@alice", "@alice", aToken))

        assertThat(response.follows).isEqualTo(listOf("@bob", "@juan"))
    }

    @Test
    fun `followers returns users following another user`() {
        relationships.add(Relationship(alice, bob))
        relationships.add(Relationship(alice, juan))

        val response = followers.exec(Followers.Request("@bob", "@bob", aToken))

        assertThat(response.followers).isEqualTo(
            listOf("@alice")
        )
    }

    @Test
    fun `following returns empty list when user has no follows`() {
        val response = following.exec(Request("@alice", "@alice", aToken))

        assertThat(response.follows).isEmpty()
    }

    @Test
    fun `followers returns an empty list when user has no followers`() {
        val response = followers.exec(Followers.Request("@alice", "@alice", aToken))

        assertThat(response.followers).isEmpty()
    }

    @Test
    fun `followers returns users following`() {
        relationships.add(Relationship(alice, bob))
        relationships.add(Relationship(juan, bob))

        val response = following.exec(Request("@bob", "@alice", aToken))

        assertThat(response.follows).isEqualTo(listOf("@bob"))
    }

    @Test
    fun `followers returns empty list when user has no followers`() {
        val response = following.exec(Request("@bob", "@alice", aToken))

        assertThat(response.follows).isEmpty()
    }

    @Test
    fun `users must be authenticated to request list of following users`() {
        val authService = AuthService()
        val following = Following(users, relationships, authService)

        assertThrows<UserNotAuthenticatedError> {
            following.exec(Request("@alice", "@alice", aToken))
        }
    }

    @Test
    fun `users must be authenticated to request list of followers users`() {
        val authService = AuthService()
        val followers = Followers(users, relationships, authService)

        assertThrows<UserNotAuthenticatedError> {
            followers.exec(Followers.Request("@alice", "@alice", aToken))
        }
    }

    @BeforeEach
    fun setup() {
        users.add(alice)
        users.add(bob)
        users.add(juan)
        every { authService.validateToken(any(), any()) } returns true
    }

    private val alice = User("@alice", "1234")
    private val juan = User("@juan", "1234")
    private val bob = User("@bob", "1234")
    private val aToken = SessionToken("a token")
    private val users = InMemoryUsers()
    private val authService = mockk<AuthService>()
    private val relationships = InMemoryRelationships()
    private val following = Following(users, relationships, authService)
    private val followers = Followers(users, relationships, authService)
}
