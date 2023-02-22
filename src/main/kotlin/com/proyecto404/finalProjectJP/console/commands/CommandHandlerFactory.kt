package com.proyecto404.finalProjectJP.console.commands

import com.proyecto404.finalProjectJP.console.commands.exceptions.InvalidCommandException
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.Core

class CommandHandlerFactory(private val core: Core, private val output: Output) {
    fun createFor(name: String): CommandHandler {
        return when (name) {
            "login" -> LoginHandler(core, output)
            else -> throw InvalidCommandException()
        }
    }
}
