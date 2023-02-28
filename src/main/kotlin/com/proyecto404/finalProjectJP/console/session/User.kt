package com.proyecto404.finalProjectJP.console.session

import com.proyecto404.finalProjectJP.core.domain.SessionToken

class User(val username: String, val token: SessionToken): Session() {
    override fun isAuthenticated() = true

    override fun getSession() = this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (token != other.token) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + token.hashCode()
        return result
    }
}