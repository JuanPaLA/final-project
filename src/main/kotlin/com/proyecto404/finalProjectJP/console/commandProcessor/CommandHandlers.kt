package com.proyecto404.finalProjectJP.console.commandProcessor

import com.proyecto404.finalProjectJP.console.commandProcessor.exceptions.CommandNotFoundError

class CommandHandlers(private val handlers: List<CommandHandler>) {
    fun get(command: Command) = handlers.singleOrNull { it.name == command.name } ?: throw CommandNotFoundError(command.name)
}