package com.proyecto404.finalProjectJP.console.session

class AnonymousSession: SessionType() {
    override fun isAuthenticated() = false

    override fun getSession() = throw NotUserSessionAuthenticatedError()
}