package com.proyecto404.finalProjectJP.console.commands

import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.Login

class LoginCommandHandler(val core: Core, val output: Output) : CommandHandler {
    override fun handle(command: Command) {
        val commandParts = command.args.joinToString(" ").split(" ")
        val username = commandParts[0]
        val password = commandParts[1]
        val response = core.login().exec(Login.Request(username, password))

    }
}
