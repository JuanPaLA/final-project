package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Following (private val users: Users, private val relationships: Relationships, private val authService: AuthService) {
    fun exec(request: Request): Response {
        val requesterUser = users.get(request.requester)
        authService.validateToken(request.token, requesterUser)
        val followedUser = users.get(request.following)
        val follows = relationships.getFollowings(followedUser.name)
        return Response(follows)
    }

    data class Request(val requester: String, val following: String, val token: SessionToken)
    data class Response(val follows: List<String>)
}
