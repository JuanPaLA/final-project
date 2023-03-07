package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Unfollow

class UnfollowHandler(private val output: Output, private val core: Core, private val session: SessionState) : CommandHandler {
    override val name = "unfollow"

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        try {
            val requesterUserName = session.getSession().username
            val token = session.getSession().token
            val unfollowedUserName = command.args[0]
            tryRequest(requesterUserName, unfollowedUserName, token)
        } catch(e: IndexOutOfBoundsException) {
            output.println("ERROR: A username must be given to be unfollowed")
        }
    }

    private fun tryRequest(requesterUserName: String, unfollowedUserName: String, token: SessionToken) {
        try {
            core.unfollow().exec(Unfollow.Request(requesterUserName, unfollowedUserName, token))
        } catch (e: UserNotFoundError) {
            output.println("ERROR: user $unfollowedUserName not found")
        }
    }

    private fun isNotAuthenticated(): Boolean {
        if (!session.isAuthenticated()) {
            output.println("ERROR: User must be logged in to follow users")
            return true
        }
        return false
    }
}
