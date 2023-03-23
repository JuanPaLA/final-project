package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidLoginCredentialsError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.useCases.Login.Request
import io.javalin.Javalin
import io.javalin.http.Context

class AuthController(private val http: Javalin, private val core: Core) {
    init {
        http.post("/login", ::login)
    }

    private fun login(ctx: Context) {
        try {
            val json = getJsonFrom(ctx)
            val userName = json.get("name").asString()
            val password = json.get("password").asString()
            val token = core.login().exec(Request(userName, password))
            val response = JsonObject().add("token", token.sessionToken.toString()).toString()
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

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
