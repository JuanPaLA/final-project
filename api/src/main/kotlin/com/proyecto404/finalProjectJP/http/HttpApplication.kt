package com.proyecto404.finalProjectJP.http

import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.http.controllers.*
import io.javalin.Javalin

class HttpApplication(private val config: Configuration) {

    private val httpServer = Javalin.create{
        config ->
        config.enableCorsForAllOrigins()
    }

    init {
        UserController(httpServer, config.core)
        AuthController(httpServer, config.core)
        PostController(httpServer, config.core)
        FollowingController(httpServer, config.core)
        WallController(httpServer, config.core)
    }

    fun start() {
        httpServer.start(config.port)
    }

    fun stop() {
        httpServer.stop()
    }

    data class Configuration(val port: Int, val core: Core)
}
