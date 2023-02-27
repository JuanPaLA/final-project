package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.useCases.SignUp
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Login

class Core {
    private val users = InMemoryUsers()

    fun signup() = SignUp(users)
    fun login() = Login(users, AuthService())
}
