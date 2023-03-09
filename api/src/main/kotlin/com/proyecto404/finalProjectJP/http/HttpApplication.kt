package com.proyecto404.finalProjectJP.http

import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.http.controllers.UserController
import io.javalin.Javalin
import io.javalin.config.JavalinConfig
import io.javalin.plugin.bundled.CorsContainer
import io.javalin.plugin.bundled.CorsPluginConfig

class HttpApplication(private val config: Configuration) {
    private val httpServer = Javalin.create { config: JavalinConfig ->
        config.plugins.enableCors { cors: CorsContainer -> cors.add { it: CorsPluginConfig -> it.anyHost() } }
    };

    init {
        UserController(httpServer, config.core)
    }

    fun start() {
        httpServer.start(config.port)
    }

    fun stop() {
        httpServer.stop()
    }

    data class Configuration(val port: Int, val core: Core)
}
