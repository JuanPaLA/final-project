package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.Relationships
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Follow(private val relationships: Relationships, private val users: Users, private val authService: AuthService) {
    fun exec(request: Request) {
        val requestUser = users.get(request.requestUserName)
        val followUser = users.get(request.followUserName)
        val token = request.token
        authService.validateToken(token, requestUser)
        relationships.add(Relationship(requestUser, followUser))
    }

    data class Request(val requestUserName: String, val followUserName: String, val token: SessionToken)
}
