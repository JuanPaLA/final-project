package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.LogoutHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.session.Anonymous
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.console.session.User
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.Core.Configuration
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryRepositoryProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LogoutHandlerTest {
    @Test
    fun `logout erase logged user`() {
        handler.execute(Command("logout", listOf()))

        assertThat(session.identity).isEqualTo(Anonymous())
    }

    @Test
    fun `logout is successful with logged in users`() {
        session.authenticate(User("@alice", SessionToken("1234")))

        handler.execute(Command("logout", listOf()))

        assertThat(output.lines).endsWith("Logged out!")
    }

    @Test
    fun `logout fails without logged in users`() {
        handler.execute(Command("logout", listOf()))

        assertThat(output.lines).endsWith(
            "You must be logged in to logout",
        )
    }

    private val output = FakeOutput()
    private val core = Core(Configuration(InMemoryRepositoryProvider()))
    private val session = SessionState()
    private val handler = LogoutHandler(output, core, session)
}
