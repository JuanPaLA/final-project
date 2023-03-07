package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Follow.Request

class FollowHandler(private val output: Output, private val core: Core, private val session: SessionState) :
    CommandHandler {
    override val name = "follow"

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        try {
            val requestUserName = session.getSession().username
            val token = session.getSession().token
            val followedUserName = command.args[0]
            tryRequest(requestUserName, followedUserName, token)
        } catch (e: IndexOutOfBoundsException) {
            output.println("ERROR: a username to follow must be provided")
        }
    }

    private fun tryRequest(requestUserName: String, followedUserName: String, token: SessionToken) {
        try {
            core.follow().exec(Request(requestUserName, followedUserName, token))
        } catch (e: UserNotFoundError) {
            output.println("ERROR: Unknown user $followedUserName")
        }
    }

    private fun isNotAuthenticated(): Boolean {
        if (!session.isAuthenticated()) {
            output.println("ERROR: User must be logged in to follow other users")
            return true
        }
        return false
    }

}
