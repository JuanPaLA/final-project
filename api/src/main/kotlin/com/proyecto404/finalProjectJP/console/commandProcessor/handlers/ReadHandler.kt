package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.PostService
import com.proyecto404.finalProjectJP.core.useCases.Read
import java.time.LocalDateTime
import java.util.*

class ReadHandler(private val output: Output, private val core: Core, private val session: SessionState) : CommandHandler {
    override val name = "read"
    private val postService = PostService() //¿dónde tiene que definirse este service? del lado de la consola?

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        try {
            val userRequester = session.getSession().username
            val token = session.getSession().token
            val postAuthor = command.args[0]
            if (isEmpty(postAuthor)) return
            val response = core.read().exec(Read.Request(userRequester, postAuthor, token))
            printPosts(response, postAuthor)
        } catch (e: IndexOutOfBoundsException) {
            output.println("ERROR: Must specify an user")
        } catch (e: UserNotFoundError) {
            output.println("ERROR: Unknown user ${command.args[0]}")
        }
    }

    private fun printPosts(response: Read.Response, author: String) {
        if (response.posts.isNotEmpty())
            for (post in response.posts) {
                output.println("- " + post.content + stringPassedTime(post.date))
        } else output.println("ERROR: There are no post from $author")
    }

    private fun stringPassedTime(date: LocalDateTime): String {
        val timePassed = postService.calculateTimePassedFromPosting(date)
        val hours = timePassed.toHours()
        val minutes = timePassed.toMinutes()
        return if ((hours >= minutes) && (hours.toInt() != 0)) " ($hours hours ago)"
        else " ($minutes minutes ago)"
    }

    private fun isNotAuthenticated(): Boolean {
        if (!session.isAuthenticated()) {
            output.println("ERROR: User must be logged to post messages")
            return true
        }
        return false
    }

    private fun isEmpty(postAuthor: String): Boolean {
        if (postAuthor.isEmpty()) {
            output.println("ERROR: Must specify an user")
            return true
        }
        return false
    }
}
