package com.proyecto404.finalProjectJP.console.session

import com.proyecto404.finalProjectJP.console.commandProcessor.Prompt
import com.proyecto404.finalProjectJP.console.session.SessionState

class SessionPrinter(val sessionState: SessionState): Prompt {
    override fun prompt(): String {
        return if (!sessionState.isAuthenticated()) {
            "> "
        } else {
            val username = sessionState.getSession().username
            "$username> "
        }
    }

    override fun toString(): String {
        return this.prompt()
    }
}
