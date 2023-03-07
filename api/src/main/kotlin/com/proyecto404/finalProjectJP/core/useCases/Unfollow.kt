package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.Relationships
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class Unfollow(private val users: Users, private val relationships: Relationships, private val authService: AuthService) {

    fun exec(request: Request) {
        val requestUser = users.get(request.requesterUserName)
        authService.validateToken(request.token, requestUser)
        val unfollowedUser = users.get(request.unfollowedUserName)
        relationships.remove(Relationship(requestUser, unfollowedUser))
    }

    data class Request(val requesterUserName: String, val unfollowedUserName: String, val token: SessionToken)
}
