package com.proyecto404.finalProjectJP.console.commandProcessor

interface CommandHandler {
    val name: String
    fun execute(arguments: List<String>)
}
