package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Followers(private val users: Users, private val relationships: Relationships, private val authService: AuthService) {
    fun exec(request: Request): Response {
        val followers = relationships.getFollowers(request.fromUser)
        authService.validateToken(request.token, users.get(request.requester))
        return Response(followers)
    }
    data class Request(val requester: String, val fromUser: String, val token: SessionToken)
    data class Response(val followers: List<String>)
}
