package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.domain.useCases.Login
import com.proyecto404.finalProjectJP.core.domain.useCases.Login.Request

class LoginHandler(override val name: String, private val output: Output, private val useCase: Login): CommandHandler {
    override fun execute(command: Command) {
        val response = useCase.exec(Request(command.args[0], command.args[1]))
        printCommand(command.args)
        if (response.isLogged) {
            output.println("Logged in as ${command.args[0]}!")
        } else {
            output.println("ERROR: Invalid credentials for ${command.args[0]}")
        }
    }

    private fun printCommand(arguments: List<String>) {
        output.print(name)
        if (arguments.isNotEmpty()) output.print(" " + arguments.joinToString(" "))
        output.print("\n")
    }
}