package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRelationships
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Follow.Request
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FollowTest {
    @Test
    fun `follow creates new following relationship`() {
        follow.exec(Request("@alice", "@bob", aToken))
        follow.exec(Request("@juan", "@bob", aToken))

        assertThat(relationships.getFollowers("@bob")).isEqualTo(listOf("@alice", "@juan"))
    }

    @Test
    fun `follow creates new follower relationship`() {
        follow.exec(Request("@alice", "@bob", aToken))
        follow.exec(Request("@juan", "@bob", aToken))

        assertThat(relationships.getFollowings("@alice")).isEqualTo(listOf("@bob"))
    }

    @Test
    fun `users can not request follow to an already followed user`() {
        follow.exec(Request("@alice", "@bob", aToken))
        follow.exec(Request("@alice", "@bob", aToken))

        assertThat(relationships.getFollowings("@alice")).isEqualTo(listOf("@bob"))
    }

    @Test
    fun `followed user must exist to be followed`() {
        assertThrows<UserNotFoundError> {
            follow.exec(Request("@alice", "@nonExistingUser", aToken))
        }
    }

    @Test
    fun `users must be authenticated to follow other user`() {
        val authService = AuthService()

        val follow = Follow(relationships, users, authService)

        assertThrows<UserNotAuthenticatedError> {
            follow.exec(Request("@alice", "@bob", aToken))
        }
    }

    @BeforeEach
    fun setup() {
        users.add(User(1, "@alice", "1234"))
        users.add(User(2, "@bob", "1234"))
        users.add(User(3, "@juan", "1234"))
        every { authService.validateToken(any(), any()) } returns true
    }

    private val aToken = SessionToken("a token")
    private val users = InMemoryUsers()
    private val authService = mockk<AuthService>(relaxed = true)
    private val relationships = InMemoryRelationships()
    private val follow = Follow(relationships, users, authService)
}
