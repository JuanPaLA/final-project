package com.proyecto404.finalProjectJP.console.commandProcessor

class CommandHandlers(private val handlers: List<CommandHandler>) {
    fun get(name: String) = handlers.singleOrNull { it.name == name } ?: throw CommandNotFoundError(name)
}