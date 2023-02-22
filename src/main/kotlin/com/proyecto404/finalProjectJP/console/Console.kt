package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commands.Command
import com.proyecto404.finalProjectJP.console.commands.CommandHandlerFactory
import com.proyecto404.finalProjectJP.console.io.Input
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.Core

class Console(private val input: Input, private val output: Output) {
    private val core = Core();

    val commandHandlerFactory = CommandHandlerFactory(core, output)

    fun run(){
        val command = readCommand()
        execute(command)
    }

    fun readCommand(): Command {
        return Command.fromString(input.readln())
    }

    fun execute(command: Command) {
        val handler = commandHandlerFactory.createFor(command.name)
        handler.handle(command)
    }
}
