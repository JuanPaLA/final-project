package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.core.useCases.SignUp
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers

class Core {
    private val users = InMemoryUsers()

    fun signup() = SignUp(users)
}
