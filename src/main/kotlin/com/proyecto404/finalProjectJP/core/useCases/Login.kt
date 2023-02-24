package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.SessionToken

class Login(private val users: Users){
    fun exec(request: Request): Response {
        TODO()
    }
    data class Request(val userName: String, val password: String)
    data class Response(val status: Boolean, val message: String, val sessionToken: SessionToken)
}
