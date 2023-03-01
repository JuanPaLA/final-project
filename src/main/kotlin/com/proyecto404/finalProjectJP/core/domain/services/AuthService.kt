@file:Suppress("PrivatePropertyName")

package com.proyecto404.finalProjectJP.core.domain.services

import com.proyecto404.finalProjectJP.core.domain.SessionToken
import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidPasswordError
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidUsernameError
import com.proyecto404.finalProjectJP.core.useCases.Login.*

class AuthService() {
    private val ARGS_MIN_LENGTH = 4

    fun checkCredentials(user: User) {
        if (user.name.isEmpty()) throw InvalidUsernameError()
        if (user.password.isEmpty()) throw InvalidPasswordError()
        if (user.name.first().toString() != "@") throw InvalidUsernameError()
        if (user.name.length < ARGS_MIN_LENGTH) throw InvalidUsernameError()
        if (user.password.length < ARGS_MIN_LENGTH) throw InvalidPasswordError()
    }

    fun generateSessionToken(user: User) = SessionToken(user.name.reversed())

    fun isValidCredentialRequest(request: Request, user: User): Boolean {
        return User(request.userName, request.password) == user
    }

}