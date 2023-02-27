package com.proyecto404.finalProjectJP.console.session

class AuthSession(val userSession: UserSession) : SessionType() {
    override fun isAuthenticated(): Boolean = true

    override fun getSession(): UserSession {
        return this.userSession
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthSession

        if (userSession != other.userSession) return false

        return true
    }

    override fun hashCode(): Int {
        return userSession.hashCode()
    }
}
