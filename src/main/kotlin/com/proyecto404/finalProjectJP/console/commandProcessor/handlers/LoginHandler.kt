@file:Suppress("PrivatePropertyName")

package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.console.session.User
import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.Login.Request

class LoginHandler(private val output: Output, private val core: Core, private var sessionState: SessionState): CommandHandler {
    override val name = "login"
    private val ARGS_MIN_LENGTH = 4

    override fun execute(command: Command) {
        val userName = command.args[0]
        val password = command.args[1]
        if (isEmpty(userName)) return
        if (isInvalid(userName)) return
        if (isLongEnough(userName)) return
        if (isLongEnough(password)) return
        val response = core.login().exec(Request(userName, password))
        if (response.sessionToken != null) {
            sessionState.authenticate(User(userName, response.sessionToken))
            output.println("Logged in as ${userName}!")
        } else {
            output.println("ERROR: Invalid credentials for $userName")
        }
    }

    private fun isEmpty(userName: String): Boolean {
        if (userName.isEmpty()) {
            output.println("ERROR: username and password must be at least 4 characters long")
            return true
        }
        return false
    }

    private fun isLongEnough(userName: String): Boolean {
        if (userName.length < ARGS_MIN_LENGTH) {
            output.println("ERROR: username and password must be at least 4 characters long")
            return true
        }
        return false
    }

    private fun isInvalid(userName: String): Boolean {
        if (userName.first().toString() != "@") {
            output.println("ERROR: username must start with @")
            return true
        }
        return false
    }
}
