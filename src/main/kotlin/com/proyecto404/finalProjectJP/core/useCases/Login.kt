package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.SessionToken
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService

class Login(private val users: Users, private val authService: AuthService) {

    fun exec(request: Request): Response {
        val user = users.get(request.userName)
        return if (authService.isValidCredentialRequest(request, user)) {
            val sessionToken = authService.generateSessionToken(user)
            user.tokens.add(sessionToken)
            users.update(user)
            Response(sessionToken)
        } else {
            Response(null)
        }
    }

    data class Request(val userName: String, val password: String)
    data class Response(val sessionToken: SessionToken?)
}
