package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService

class Login(private val users: Users, private val authService: AuthService) {

    fun exec(request: Request): Response {
        val user = users.get(request.userName)
        authService.isValidCredentialRequest(request, user)
        val sessionToken = authService.generateSessionToken(user)
        user.addToken(sessionToken)
        users.update(user)
        return Response(sessionToken)
    }

    data class Request(val userName: String, val password: String)
    data class Response(val sessionToken: SessionToken?)
}
