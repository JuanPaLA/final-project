package com.proyecto404.finalProjectJP.core.domain

import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken

class User(val name: String, val password: String) {
    private var id: Int = 0
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

    fun snapshot(): UserSnapshot {
        return UserSnapshot(id, name, password, sessionToken()?.toString())
    }

    private fun sessionToken(): SessionToken? {
        return if (tokens.isEmpty()) null else tokens.last()
    }

    companion object {
        fun from(snapshot: UserSnapshot): User {
            return User(snapshot.name, snapshot.password)
                .also {
                    it.id = snapshot.id
                    if (snapshot.sessionToken != null) it.addToken(SessionToken(snapshot.sessionToken))
                }
        }
    }

    data class UserSnapshot(var id: Int, val name: String, val password: String, val sessionToken: String?)
}
