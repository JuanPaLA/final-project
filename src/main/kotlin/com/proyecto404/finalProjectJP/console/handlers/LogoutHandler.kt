package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.Anonymous
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core

class LogoutHandler(val output: Output,val core: Core, val session: SessionState) : CommandHandler {
    override val name = "logout"
    override fun execute(command: Command) {
        if (session.identity == Anonymous()) {
            output.println("You must be logged in to logout")
        } else {
            session.logout()
            output.println("Logged out!")
        }
    }

}
