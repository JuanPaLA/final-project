package com.proyecto404.finalProjectJP.core.domain

import java.time.LocalDateTime
import java.util.Date

class Post(val id: Int, val userId: String, val content: String, val date: LocalDateTime = LocalDateTime.now()) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + userId.hashCode()
        result = 31 * result + content.hashCode()
        return result
    }
}
