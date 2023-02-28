package com.proyecto404.finalProjectJP.console.session

abstract class Session {
    abstract fun isAuthenticated(): Boolean

    abstract fun getSession(): User

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
