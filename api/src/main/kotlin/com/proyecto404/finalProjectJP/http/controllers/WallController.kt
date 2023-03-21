package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Wall.Request
import io.javalin.Javalin
import io.javalin.http.Context

class WallController(private val http: Javalin, private val core: Core) {
    init {
        http.get("/walls/{account}", ::getTimeline)
    }

    private fun getTimeline(ctx: Context) {
        val token = SessionToken(ctx.req.getHeader("Authorization"))
        val user = ctx.pathParam("account")
        val wallPosts = core.wall().exec(Request(user, token))
        val postsArray = Json.array()
        wallPosts.timeline.forEach {
            postsArray.add(
                JsonObject()
                .add("id", it.id)
                .add("content", it.content)
                .add("author", it.userId)
                .add("date", it.date.toString())
            )
        }
        val response = JsonObject().add("posts", postsArray).toString()
        ctx.status(200).json(response)
    }
}
