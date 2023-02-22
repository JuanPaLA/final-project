package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.Users

class Login(val users: Users) {

    fun exec(request: Request): Response{
        return Response(true)
    }

    data class Request(val username: String, val password: String)
    data class Response(val isLogged: Boolean)
}