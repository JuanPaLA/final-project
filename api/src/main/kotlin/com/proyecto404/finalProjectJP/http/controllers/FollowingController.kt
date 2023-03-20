package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Follow
import io.javalin.Javalin
import io.javalin.http.Context

class FollowingController(private val http: Javalin, private val core: Core) {
    init {
        http.post("/follows", ::follow)
    }

    private fun follow(ctx: Context) {
        val json = getJsonFrom(ctx)
        val follower = json.get("follower").asString()
        val followee = json.get("followee").asString()
        val sessionToken = ctx.req.getHeader("Authorization")
        val token = SessionToken(sessionToken)
        core.follow().exec(Follow.Request(follower, followee, token))
        ctx.status(201)
    }

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
