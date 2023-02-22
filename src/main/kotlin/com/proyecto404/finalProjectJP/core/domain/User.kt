package com.proyecto404.finalProjectJP.core.domain

class User(private val userId: UserId, private val userName: String) {
    private val sessionTokens = mutableListOf<SessionToken>()
}

class SessionToken {
    private val value: String = ""
    private val expirationDate: String = ""
}

class UserId(val value: Int){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserId

        if (value != other.value) return false

        return true
    }
    override fun hashCode(): Int {
        return value
    }
}