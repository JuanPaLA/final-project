package com.proyecto404.finalProjectJP.console.session

import com.proyecto404.finalProjectJP.console.session.exceptions.NotUserSessionAuthenticatedError

class Anonymous: Session() {
    override fun isAuthenticated() = false

    override fun getSession() = throw NotUserSessionAuthenticatedError()
}