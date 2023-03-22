package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.*
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Post
import com.proyecto404.finalProjectJP.core.useCases.Read.Request
import io.javalin.Javalin
import io.javalin.http.Context

class PostController(private val http: Javalin, private val core: Core) {

    init {
        http.post("/posts", ::createPost)
        http.get("/posts/{author}", ::getPostsByAuthor)
    }

    private fun getPostsByAuthor(ctx: Context) {
        try {
            val sessionToken = ctx.req.getHeader("Authorization")
            val requester = ctx.req.getHeader("Requester")
            val token = SessionToken(sessionToken)
            val author = ctx.pathParam("author")
            val posts = core.read().exec(Request(requester, author, token))
            val postsArray = Json.array()
            posts.posts.forEach {
                postsArray.add(JsonObject()
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

    private fun createPost(ctx: Context) {
        try {
            val sessionToken = ctx.req.getHeader("Authorization")
            val json = getJsonFrom(ctx)
            val userName = json.get("username").asString()
            val token = SessionToken(sessionToken)
            val content = json.get("content").asString()
            core.post().exec(Post.Request(userName, token, content))
            ctx.status(201)
        } catch (e: InvalidUsernameError) {
            ctx.status(401)
        } catch (e: EmptyPostError) {
            ctx.status(400)
        } catch (e: PostMaxedLengthError) {
            ctx.status(400)
        } catch (e: UserNotFoundError) {
            ctx.status(404)
        } catch (e: UserNotAuthenticatedError) {
            ctx.status(403)
        } catch (e: Exception) {
            val response = JsonObject().add("error", e.message).toString()
            ctx.status(500).json(response)
        }
    }

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
