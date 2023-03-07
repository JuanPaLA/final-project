package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryPosts
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Read.Request
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.proyecto404.finalProjectJP.core.domain.User as UserEntity

class ReadTest {
    @Test
    fun `read given user's post`() {
        posts.add(listOfPosts[0])
        posts.add(listOfPosts[1])

        val response = read.exec(Request("@alice", "@alice", aToken))

        assertThat(response).isEqualTo(Read.Response(listOfPosts))
    }

    @Test
    fun `author's name must be given to read its posts`() {
        val read = Read(posts, users, authService)

        assertThrows<UserNotFoundError> {
            read.exec(Request("@alice", "", aToken))
        }
    }

    @Test
    fun `users must be authenticated in to read user's posts`() {
        val authService = AuthService()

        val read = Read(posts, users, authService)

        assertThrows<UserNotAuthenticatedError> {
            read.exec(Request("@alice", "@alice", aToken))
        }
    }

    @Test
    fun `post's author must exist to read its posts`() {
        val read = Read(posts, users, authService)

        assertThrows<UserNotFoundError> {
            read.exec(Request("@alice", "@bob", aToken))
        }
    }

    @BeforeEach
    fun setup() {
        users.add(alice)
        every { authService.validateToken(any(), any()) } returns true
    }

    private val alice = UserEntity("@alice", "1234")
    private val posts = InMemoryPosts()
    private val users = InMemoryUsers()
    private val authService = mockk<AuthService>()
    private val read = Read(posts, users, authService)
    private val aToken = SessionToken("aToken")
    private val listOfPosts = listOf<Post>(
        Post(1, "@alice", "Good game though."),
        Post(1, "@alice", "Damn! We lost!")
    )
}
