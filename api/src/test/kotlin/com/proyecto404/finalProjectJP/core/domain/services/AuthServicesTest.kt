package com.proyecto404.finalProjectJP.core.domain.services

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AuthServicesTest {
    @Test
    fun `users are authenticated with valid credentials`() {
        alice.addToken(aToken)

        assertThrows<UserNotAuthenticatedError> {
            authService.validateToken(otherToken, alice)
        }
    }

    private val authService = AuthService()
    private val otherToken = SessionToken("otherToken")
    private val aToken = SessionToken("aToken")
    private val alice = User("@alice", "1234")
}