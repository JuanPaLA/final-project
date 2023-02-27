package com.proyecto404.finalProjectJP.core.domain

class User (val name: String, val password: String){
    val tokens: MutableList<SessionToken> = mutableListOf()

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
