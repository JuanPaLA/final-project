package com.proyecto404.finalProjectJP.console.session

class SessionState {
    var identity: Session = Anonymous()

    fun authenticate(user: User) {
        this.identity = user
    }

    fun isAuthenticated() = identity is User

    fun logout() {
        this.identity = Anonymous()
    }

    fun getSession(): User {
        if (isAuthenticated()) return identity as User
        else throw NotLoggedInError
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Session

        if (identity != other) return false

        return true
    }

    override fun hashCode(): Int {
        return identity.hashCode()
    }
}
