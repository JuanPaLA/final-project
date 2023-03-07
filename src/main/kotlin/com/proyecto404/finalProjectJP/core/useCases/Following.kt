package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Relationships
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Following (private val users: Users, private val relationships: Relationships, private val authService: AuthService) {
    fun exec(request: Request): Response {
        val requesterUser = users.get(request.requesterUserName)
        authService.validateToken(request.token, requesterUser)
        val followedUser = users.get(request.usersFollowing)
        val follows = relationships.getFollowings(followedUser.name)
        return Response(follows)
    }

    data class Request(val requesterUserName: String, val usersFollowing: String, val token: SessionToken)
    data class Response(val follows: List<String>)
}
