@file:Suppress("PrivatePropertyName")

package com.proyecto404.finalProjectJP.console.commandProcessor

import com.proyecto404.finalProjectJP.console.commandProcessor.exceptions.CommandNotFoundError
import com.proyecto404.finalProjectJP.console.io.Input
import com.proyecto404.finalProjectJP.console.io.Output

class CommandProcessor(private val input: Input, private val output: Output, handlers: List<CommandHandler>, private val prompt: Prompt) {
    private val handlers = CommandHandlers(handlers)
    private val EXIT_COMMAND = "exit"

    fun run() {
        while (true) {
            val command = readCommand()
            if (command.name == EXIT_COMMAND) break
            execute(command)
        }
        printExitMessage()
    }

    private fun readCommand(): Command {
        printPrompt()
        return Command.fromString(input.readln())
    }

    private fun printPrompt() {
        output.print(prompt.toString())
    }

    private fun execute(command: Command) {
        try {
            handlers.get(command).execute(command)
        } catch (e: CommandNotFoundError) {
            output.println("ERROR: Invalid command ${command.name}")
        }
    }

    private fun printExitMessage() {
        output.println("bye bye")
    }
}
