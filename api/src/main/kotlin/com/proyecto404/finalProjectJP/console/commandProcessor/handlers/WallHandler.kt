@file:Suppress("DuplicatedCode")

package com.proyecto404.finalProjectJP.console.commandProcessor.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.services.PostService
import com.proyecto404.finalProjectJP.core.useCases.Wall.Request
import java.util.*

class WallHandler(private val output: Output, private val core: Core, private val session: SessionState) : CommandHandler {
    override val name = "wall"
    private val postService = PostService()

    override fun execute(command: Command) {
        if (isNotAuthenticated()) return
        val requestUserName = session.getSession().username
        val token = session.getSession().token
        val response = core.wall().exec(Request(requestUserName, token))
        printPosts(response.timeline)
    }

    private fun isNotAuthenticated(): Boolean {
        if (!session.isAuthenticated()) {
            output.println("ERROR: User must be logged in to request timeline wall")
            return true
        }
        return false
    }

    private fun printPosts(posts: List<Post>) {
        if (posts.isNotEmpty())
            for (post in posts) output.println("- ${post.userId}: " + post.content + stringPassedTime(post.date))
        else output.println("ERROR: No posts in your timeline")
    }

    private fun stringPassedTime(date: Date): String {
        val timePassed = postService.calculateTimePassedFromPosting(date)
        val hours = timePassed.toHours()
        val minutes = timePassed.toMinutes()
        return if ((hours >= minutes) && (hours.toInt() != 0)) " ($hours hours ago)"
        else " ($minutes minutes ago)"
    }
}
