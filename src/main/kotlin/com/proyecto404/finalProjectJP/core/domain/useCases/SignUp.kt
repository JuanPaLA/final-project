package com.proyecto404.finalProjectJP.core.domain.useCases

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.Users

class SignUp(private val users: Users) {
    fun exec(request: Request): Response {
        val userName = request.userName
        val password = request.password
        val newUser = User(userName, password)
        val existingUser = users.get(userName)
        return if (existingUser.name == userName) {
            Response(false, "ERROR: User $userName already exists")
        } else {
            users.add(newUser)
            Response(true, "successful signup")
        }
    }

    data class Request(val userName: String, val password: String)
    data class Response(val status: Boolean, val message: String)
}
