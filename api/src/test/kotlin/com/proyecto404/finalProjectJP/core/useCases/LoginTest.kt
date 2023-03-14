package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidLoginCredentialsError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Login.*
import com.proyecto404.finalProjectJP.core.useCases.SignUp.Request
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LoginTest {
    @Test
    fun `registered users can login with its credentials`() {
        every { authService.generateSessionToken(any()) } returns SessionToken("aToken")
        every { authService.isValidCredentialRequest(any(), any()) } returns true
        signup.exec(Request("@alice", "1234"))

        login.exec(Login.Request("@alice", "1234"))

        val alice = users.get("@alice")
        assertThat(alice.token()).isEqualTo(SessionToken("aToken"))
    }

    @Test
    fun `unregistered users cannot login`() {
        assertThrows<UserNotFoundError> {
            login.exec(Login.Request("@alice", "1234"))
        }
    }

    @Test
    fun `user cannot login with invalid username`() {
        signup.exec(Request("@alice", "1234"))

        assertThrows<UserNotFoundError> {
            login.exec(Login.Request("alice", "1234"))
        }
    }

    @Test
    fun `users cannot login with invalid password`() {
        val login = Login(users, AuthService())

        signup.exec(Request("@alice", "1234"))

        assertThrows<InvalidLoginCredentialsError> {
            login.exec(Login.Request("@alice", "1111"))
        }
    }

    @Test
    fun `user cannot login with empty username`() {
        signup.exec(Request("@alice", "1234"))

        assertThrows<UserNotFoundError> {
            login.exec(Login.Request("", "1234"))
        }
    }

    @Test
    fun `user cannot login with empty password`() {
        val login = Login(users, AuthService())
        signup.exec(Request("@alice", "1234"))

        assertThrows<InvalidLoginCredentialsError> {
            login.exec(Login.Request("@alice", ""))
        }
    }

    private val users = InMemoryUsers()
    private val authService = mockk<AuthService>(relaxed = true)
    private val login = Login(users, authService)
    private val signup = SignUp(users)
}
