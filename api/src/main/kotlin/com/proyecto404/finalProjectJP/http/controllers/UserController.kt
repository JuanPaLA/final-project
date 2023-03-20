package com.proyecto404.finalProjectJP.http.controllers

import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonObject
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.exceptions.RepeatedUsernameError
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.GetUsers
import com.proyecto404.finalProjectJP.core.useCases.SignUp
import io.javalin.Javalin
import io.javalin.http.Context

class UserController(private val http: Javalin, private val core: Core) {
    init {
        http.post("/users", ::createUser)
        http.get("/users", ::getUsers)
    }

    private fun getUsers(ctx: Context) {
        val token = SessionToken(ctx.req.getHeader("Authorization"))
        val requester = ctx.req.getHeader("Requester")
        try {
            val users = core.getUsers().exec(GetUsers.Request(requester, token))
            val usersArray = Json.array()
            users.users.forEach {
                usersArray.add(JsonObject()
                    .add("id", it.id)
                    .add("name", it.name)
                )
            }
            val response = JsonObject().add("users", usersArray).toString()
            ctx.status(200).json(response)
        } catch (e: Error) {
            val response = JsonObject().add("error", e.message)
            ctx.status(500).json(response)
        }
    }

    private fun createUser(ctx: Context) {
        val json = getJsonFrom(ctx)
        val userName = json.get("name").asString()
        val password = json.get("password").asString()
        try {
            core.signup().exec(SignUp.Request(userName, password))
            ctx.status(201)
        } catch (e: RepeatedUsernameError) {
            val response = JsonObject().add("error", "Username already exists")
            val body = JsonObject().add("body", response)
            ctx.status(409).json(body)
        } catch (e: Exception) {
            val response = JsonObject().add("error", "Unexpected error")
            val body = JsonObject().add("body", response)
            ctx.status(500).json(body)
        }
    }

    private fun getJsonFrom(ctx: Context) = Json.parse(ctx.body()).asObject()
}
