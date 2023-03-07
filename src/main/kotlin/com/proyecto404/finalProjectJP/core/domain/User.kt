package com.proyecto404.finalProjectJP.core.domain

import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError

class User (val name: String, val password: String){
    private val tokens: MutableList<SessionToken> = mutableListOf()

    fun addToken(token: SessionToken) {
        tokens.add(token)
    }

    fun token(): SessionToken {
        if (tokens.isEmpty()) throw UserNotAuthenticatedError()
        return tokens.last()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}
