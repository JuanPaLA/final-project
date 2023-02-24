package com.proyecto404.finalProjectJP.core.useCases

class Login(){
    fun exec(request: Request): Response {
        TODO()
    }
    data class Request(val userName: String, val password: String)
    data class Response(val isLogged: Boolean)
}
