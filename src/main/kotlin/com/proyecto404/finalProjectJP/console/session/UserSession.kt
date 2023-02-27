package com.proyecto404.finalProjectJP.console.session

import com.proyecto404.finalProjectJP.core.domain.SessionToken

class UserSession(val username: String, val token: SessionToken) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserSession

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