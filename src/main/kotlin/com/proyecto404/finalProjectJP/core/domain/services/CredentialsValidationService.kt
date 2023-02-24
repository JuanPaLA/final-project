@file:Suppress("PrivatePropertyName")

package com.proyecto404.finalProjectJP.core.domain.services

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.useCases.InvalidPasswordError
import com.proyecto404.finalProjectJP.core.useCases.InvalidUsernameError

class CredentialsValidationService(user: User) {
    private val ARGS_MIN_LENGTH = 4

    init {
        if (user.name.isEmpty()) throw InvalidUsernameError()
        if (user.password.isEmpty()) throw InvalidPasswordError()
        if (user.name.first().toString() != "@") throw InvalidUsernameError()
        if (user.name.length < ARGS_MIN_LENGTH) throw InvalidUsernameError()
        if (user.password.length < ARGS_MIN_LENGTH) throw InvalidPasswordError()
    }
}