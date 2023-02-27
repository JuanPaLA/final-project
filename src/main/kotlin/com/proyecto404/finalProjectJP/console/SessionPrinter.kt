package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.session.Session

class SessionPrinter(val session: Session): PromptPrinter {
    override fun prompt(): String {
        return if (session.state.isAuthenticated()) {
            val username = session.state.getSession().username
            return "$username> "
        } else {
            "> "
        }
    }
}
