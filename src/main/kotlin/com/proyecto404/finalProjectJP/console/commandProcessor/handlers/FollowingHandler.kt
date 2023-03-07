package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Following.Request
import com.proyecto404.finalProjectJP.core.useCases.Following.Response

class FollowingHandler(private val output: Output, private val core: Core, private val  session: SessionState) : CommandHandler {
    override val name = "following"

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        try {
            val requesterUserName = session.getSession().username
            val token = session.getSession().token
            val followingToUser = command.args[0]
            tryRequest(requesterUserName, followingToUser, token)
        } catch (e: IndexOutOfBoundsException) {
            output.println("ERROR: A following username must be given")
        }
    }

    private fun tryRequest(requesterUserName: String, followingToUser: String, token: SessionToken) {
        try {
            val response = core.following().exec(Request(requesterUserName, followingToUser, token))
            printFollows(response)
        } catch (e: UserNotFoundError) {
            output.println("ERROR: Following name does not exist")
        }
    }

    private fun printFollows(response: Response) {
        if (response.follows.isNotEmpty()) for (followee in response.follows) {
            output.println("- $followee")
        } else output.println("ERROR: This users is not following anyone")
    }

    private fun isNotAuthenticated(): Boolean {
        if (!session.isAuthenticated()) {
            output.println("ERROR: User must be logged in to follow users")
            return true
        }
        return false
    }
}
