package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.nbottarini.asimov.environment.Env

class Credentials(val url: String, val user: String, val password: String) {
    companion object {
        fun fromEnv(): Credentials {
            val dbUrl = "jdbc:postgresql://${Env["DB_HOST"]}:${Env["DB_PORT"]}/${Env["DB_NAME"]}"
            return Credentials(dbUrl, Env["DB_USER"]!!, Env["DB_PASSWORD"]!!)
        }
    }
}
