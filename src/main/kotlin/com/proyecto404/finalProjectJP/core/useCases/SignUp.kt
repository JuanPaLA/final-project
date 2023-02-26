package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.services.CredentialsValidationService
import com.proyecto404.finalProjectJP.core.useCases.exceptions.RepeatedUsernameError

class SignUp(private val users: Users) {
    fun exec(request: Request) {
        val userName = request.userName
        val password = request.password
        CredentialsValidationService(User(userName, password))
        users.add(User(userName, password))
    }

    data class Request(val userName: String, val password: String)
}
