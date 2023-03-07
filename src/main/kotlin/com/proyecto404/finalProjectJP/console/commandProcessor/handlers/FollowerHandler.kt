package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.Followers
import com.proyecto404.finalProjectJP.core.useCases.Followers.Request

class FollowerHandler(private val output: Output, private val core: Core, private val session: SessionState) : CommandHandler {
    override val name = "followers"

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        try {
            val requesterUserName = session.getSession().username
            val token = session.getSession().token
            val fromUser = command.args[0]
            val response = core.followers().exec(Request(requesterUserName, fromUser, token))
            printFollowers(response)
        } catch(e: IndexOutOfBoundsException) {
            output.println("ERROR: A followers username must be given")
        }
    }

    private fun printFollowers(response: Followers.Response) {
        if ( response.followers.isNotEmpty()) {
            for (follows in response.followers) {
                output.println("- $follows")
            }
        } else {
            output.println("ERROR: This user has not any follower")
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
