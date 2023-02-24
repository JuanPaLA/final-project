@file:Suppress("PrivatePropertyName")

package com.proyecto404.finalProjectJP.console.commandProcessor

import com.proyecto404.finalProjectJP.console.InvalidSignUpError
import com.proyecto404.finalProjectJP.console.io.Input
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.inMemory.UserNotFoundError

class CommandProcessor(private val input: Input, private val output: Output, handlers: List<CommandHandler>) {
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
        output.print("> ")
    }

    private fun execute(command: Command) {
        try {
            handlers.get(command).execute(command)
        } catch (e: CommandNotFoundError) {
            output.println("ERROR: Invalid command ${command.name}")
        } catch (e: InvalidSignUpError) {
            output.println("ERROR: Invalid ${command.name}")
        } catch (e: UserNotFoundError) {

        }
    }

    private fun printExitMessage() {
        output.println("bye bye")
    }
}
