package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.SessionToken
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.SignUp.Request
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LoginTest {
    @Test
    fun `user can log in with valid credentials`() {
        every { authService.generateSessionToken(any()) } returns SessionToken("aToken")
        signup.exec(Request("@alice", "1234"))

        login.exec(Login.Request("@alice", "1234"))

        val alice = users.get("@alice")
        assertThat(alice.tokens.first()).isEqualTo(SessionToken("aToken"))
    }

    private val users = InMemoryUsers()
    private val authService = mockk<AuthService>(relaxed = true)
    private val login = Login(users, authService)
    private val signup = SignUp(users)
}