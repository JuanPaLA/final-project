package com.proyecto404.finalProjectJP.core.domain.services

import com.proyecto404.finalProjectJP.core.domain.exceptions.EmptyPostError
import com.proyecto404.finalProjectJP.core.domain.exceptions.PostMaxedLengthError
import java.time.Duration
import java.util.*

class PostService {
    fun isEmpty(content: String): Boolean {
        if (content.isEmpty()) throw EmptyPostError()
        else return true
    }

    fun hasValidLength(content: String): Boolean {
        if (content.length > 140) throw PostMaxedLengthError()
        else return true
    }

    fun calculateTimePassedFromPosting(date: Date): Duration {
        val currentTime = System.currentTimeMillis()
        val dateTime = date.time
        val long = currentTime - dateTime
        return Duration.ofMillis(long)
    }
}
