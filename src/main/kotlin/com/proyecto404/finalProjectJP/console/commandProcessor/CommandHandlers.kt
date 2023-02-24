package com.proyecto404.finalProjectJP.console.commandProcessor

class CommandHandlers(private val handlers: List<CommandHandler>) {
    fun get(command: Command) = handlers.singleOrNull { it.name == command.name } ?: throw CommandNotFoundError(command.name)
}