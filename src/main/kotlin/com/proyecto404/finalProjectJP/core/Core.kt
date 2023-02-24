package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.core.domain.useCases.SignUp
import com.proyecto404.finalProjectJP.core.inMemory.InMemoryUsers

class Core {
    private val users = InMemoryUsers()

    fun signup() = SignUp(users)
}
