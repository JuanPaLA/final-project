package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class GetUsers(private val users: Users, private val authService: AuthService) {
    fun exec(request: Request): Response {
        val requester = users.get(request.requester)
        authService.validateToken(request.token,requester)
        val users = users.get()
        return Response(users)
    }

    data class Request(val requester: String, val token: SessionToken)
    data class Response(val users: List<User>)
}
