@file:Suppress("PrivatePropertyName")

package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.InvalidSignUpError
import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.useCases.SignUp

class SignUpHandler(private val output: Output, private val core: Core) : CommandHandler {
    override val name = "signup"
    private val ARGS_MIN_LENGTH = 3

    override fun execute(command: Command) {
        validateArguments(command)
        val userName = command.args[0]
        val password = command.args[1]
        validateCredentials(userName, password)
        val response = core.signup().exec(SignUp.Request(userName, password))
        if (!response.status) output.println(response.message)
    }

    private fun validateArguments(command: Command) {
        if (command.args.size != 2 || command.args.any { it.isEmpty() }) {
            throw InvalidSignUpError()
        }
    }

    private fun validateCredentials(userName: String, password: String) {
        if (userName.first() != '@' || userName.length < ARGS_MIN_LENGTH + 1 || password.length < ARGS_MIN_LENGTH || password.isEmpty()) {
            throw InvalidSignUpError()
        }
    }
}
