package com.proyecto404.finalProjectJP.console.session

class Session {
    var state: SessionType = AnonymousSession()

    fun authenticate(userSession: UserSession) {
        this.state = AuthSession(userSession)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Session

        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        return state.hashCode()
    }
}
