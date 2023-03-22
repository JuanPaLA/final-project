package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidLoginCredentialsError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Wall.Request
import io.javalin.Javalin
import io.javalin.http.Context

class WallController(private val http: Javalin, private val core: Core) {

    init {
        http.get("/walls/{account}", ::getTimeline)
    }

    private fun getTimeline(ctx: Context) {
        try {
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
        } catch (e: InvalidLoginCredentialsError) {
            val response = JsonObject().add("error", "Invalid credentials").toString()
            ctx.status(401).json(response)
        } catch (e: UserNotFoundError) {
            val response = JsonObject().add("error", "User not found").toString()
            ctx.status(404).json(response)
        } catch (e: Exception) {
            val response = JsonObject().add("error", "Internal server error").toString()
            ctx.status(500).json(response)
        }
    }
}
