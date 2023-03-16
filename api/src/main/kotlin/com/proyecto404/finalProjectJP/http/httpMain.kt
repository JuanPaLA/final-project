package com.proyecto404.finalProjectJP.http

import com.nbottarini.asimov.environment.Env
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq.JooqRepositoryProvider

fun main () {
    Env.addSearchPath("./api")
    val core = Core(Core.Configuration(JooqRepositoryProvider()))
    val httpConfig = HttpApplication.Configuration(8080, core)
    HttpApplication(httpConfig).start()
}
