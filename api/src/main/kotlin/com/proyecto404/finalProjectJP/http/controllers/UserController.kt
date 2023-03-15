package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.RepeatedUsernameError
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
        try {
            core.signup().exec(SignUp.Request(userName, password))
            ctx.status(201)
        } catch (e: RepeatedUsernameError) {
            val response = JsonObject().add("error", "Repeated username").toString()
            ctx.status(409).json(response)
        } catch (e: Exception) {
            val response = JsonObject().add("error", "Internal server error").toString()
            ctx.status(500).json(response)
        }
    }

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
