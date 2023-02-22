package com.proyecto404.finalProjectJP.core

import com.proyecto404.finalProjectJP.console.commands.Command
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Login

class Core {
    private val users : Users = InMemoryUsers()

    fun login() = Login(users)

}
