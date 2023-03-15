package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.*
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Post
import com.proyecto404.finalProjectJP.core.useCases.Read
import com.proyecto404.finalProjectJP.core.useCases.Read.*
import io.javalin.Javalin
import io.javalin.http.Context

class PostController(private val http: Javalin, private val core: Core) {
    init {
        http.post("/posts", ::createPost)
        http.get("/posts/{author}", ::getPostsByAuthor)
    }
    private fun getPostsByAuthor(ctx: Context) {
        val sessionToken = ctx.req.getHeader("Authorization")
        val requester = ctx.req.getHeader("Requester")
        val token = SessionToken(sessionToken)
        val author = ctx.pathParam("author")
        val posts = core.read().exec(Request(requester, author, token))
        val postsArray = Json.array()
        posts.posts.forEach {
            postsArray.add("id: ${it.id}, content: ${it.content}, author: ${it.userId}, date: ${it.date}")
        }
        val response = JsonObject().add("posts", postsArray).toString()
        ctx.status(200).json(response)
    }

    private fun createPost(ctx: Context) {
        val sessionToken = ctx.req.getHeader("Authorization")
        val json = getJsonFrom(ctx)
        val userName = json.get("username").asString()
        val token = SessionToken(sessionToken)
        val content = json.get("content").asString()
        try {
            core.post().exec(Post.Request(userName, token, content))
            ctx.status(201)
        } catch (e: InvalidUsernameError) {
            val response = JsonObject().add("error", "Invalid username").toString()
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
            ctx.status(500)
        }
    }

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
