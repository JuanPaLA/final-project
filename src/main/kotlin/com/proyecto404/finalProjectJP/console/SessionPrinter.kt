package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commandProcessor.Prompt
import com.proyecto404.finalProjectJP.console.session.SessionState

class SessionPrinter(val sessionState: SessionState): Prompt {
    override fun prompt(): String {
        return if (!sessionState.identity.isAuthenticated()) {
            "> "
        } else {
            val username = sessionState.identity.getSession().username
            "$username> "
        }
    }

    override fun toString(): String {
        return this.prompt()
    }
}