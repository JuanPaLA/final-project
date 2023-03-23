package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidLoginCredentialsError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Follow
import com.proyecto404.finalProjectJP.core.useCases.Followers.*
import com.proyecto404.finalProjectJP.core.useCases.Following
import com.proyecto404.finalProjectJP.core.useCases.Unfollow
import io.javalin.Javalin
import io.javalin.http.Context

class FollowingController(private val http: Javalin, private val core: Core) {
    init {
        http.post("/follows", ::follow)
        http.put("/follows", ::unfollow)
        http.get("/followers/{user}", ::getFollowers)
        http.get("/followings/{user}", ::getFollows)
    }

    private fun getFollows(ctx: Context) {
        try {
            val token = SessionToken(ctx.req.getHeader("Authorization"))
            val requester = ctx.req.getHeader("Requester")
            val user = ctx.pathParam("user")
            val followings = core.following().exec(Following.Request(requester, user, token))
            val followingsArray = Json.array()
            followings.follows.forEach {
                followingsArray.add(JsonObject().add("name", it))
            }
            val response = JsonObject().add("followings", followingsArray).toString()
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

    private fun getFollowers(ctx: Context) {
        try {
            val token = SessionToken(ctx.req.getHeader("Authorization"))
            val requester = ctx.req.getHeader("Requester")
            val user = ctx.pathParam("user")
            val followers = core.followers().exec(Request(requester, user, token))
            val followersArray = Json.array()
            followers.followers.forEach {
                followersArray.add(JsonObject().add("name", it))
            }
            val response = JsonObject().add("followers", followersArray).toString()
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

    private fun unfollow(ctx: Context) {
        try {
            val json = getJsonFrom(ctx)
            val follower = json.get("follower").asString()
            val followee = json.get("followee").asString()
            val sessionToken = ctx.req.getHeader("Authorization")
            val token = SessionToken(sessionToken)
            core.unfollow().exec(Unfollow.Request(follower, followee, token))
            ctx.status(204)
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

    private fun follow(ctx: Context) {
        try {
            val json = getJsonFrom(ctx)
            val follower = json.get("follower").asString()
            val followee = json.get("followee").asString()
            val sessionToken = ctx.req.getHeader("Authorization")
            val token = SessionToken(sessionToken)
            core.follow().exec(Follow.Request(follower, followee, token))
            ctx.status(201)
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

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
