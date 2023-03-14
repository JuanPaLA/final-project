package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.SignUp
import io.javalin.Javalin
import io.javalin.http.Context

class UserController(private val http: Javalin, private val core: Core) {
    init {
        http.post("/users", ::createUser)
    }

    private fun createUser(ctx: Context) {
        val json = getJsonFrom(ctx)
        val userName = json.get("name").asString()
        val password = json.get("password").asString()
        core.signup().exec(SignUp.Request(userName, password))
        ctx.status(200)
    }

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
