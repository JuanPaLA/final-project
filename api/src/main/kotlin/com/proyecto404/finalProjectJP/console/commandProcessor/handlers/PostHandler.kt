package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.Post

class PostHandler(private val output: Output, private val core: Core, private val sessionState: SessionState): CommandHandler {
    override val name = "post"

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        try {
            val content = command.args.joinToString(" ")
            if (isEmpty(content)) return
            if (hasInvalidLength(content)) return
            val userName = sessionState.getSession().username
            val token = sessionState.getSession().token
            core.post().exec(Post.Request(userName, token, content))
        } catch (e: IndexOutOfBoundsException) {
            output.println("ERROR: Empty message")
        }
    }

    private fun hasInvalidLength(content: String): Boolean {
        if (content.length > 140) {
            output.println("ERROR: Post can't be longer than 140 characters")
            return true
        }
        return false
    }

    private fun isNotAuthenticated(): Boolean {
        if (!sessionState.isAuthenticated()) {
            output.println("ERROR: User must be logged to post messages")
            return true
        }
        return false
    }

    private fun isEmpty(content: String): Boolean {
        if (content.isEmpty()) {
            output.println("ERROR: Empty message")
            return true
        }
        return false
    }
}