package com.proyecto404.finalProjectJP.core.domain.services

class SessionToken(private val token: String) {
    override fun toString(): String = token

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SessionToken

        if (token != other.token) return false

        return true
    }

    override fun hashCode(): Int {
        return token.hashCode()
    }
}

