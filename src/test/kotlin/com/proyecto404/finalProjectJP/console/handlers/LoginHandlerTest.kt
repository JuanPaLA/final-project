package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.session.AuthSession
import com.proyecto404.finalProjectJP.console.ConsoleApp
import com.proyecto404.finalProjectJP.console.session.UserSession
import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.LoginHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.io.InputStub
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Login
import com.proyecto404.finalProjectJP.core.useCases.Login.Request
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginHandlerTest {
    @Test
    fun `do login with given username and password`() {
        handler.execute(Command("login", listOf("@alice", "1234")))

        verify {
            login.exec(Request("@alice", "1234"))
        }
    }

    @Test
    fun `login should return confirmation message when successful`() {
        every { login.exec(any()) } returns Login.Response(SessionToken("aToken"))

        handler.execute(Command("login", listOf("@alice", "1234")))

        assertThat(output.lines).containsSequence("Logged in as @alice!")
    }

    @Test
    fun `successful login creates a session`() {
        every { login.exec(any()) } returns Login.Response(SessionToken("aToken"))

        handler.execute(Command("login", listOf("@alice", "1234")))

        assertThat(console.session.state).isEqualTo(AuthSession(UserSession("@alice", SessionToken("aToken"))))
    }

    @Test
    fun `login should return error message when unsuccessful`() {
        every { login.exec(any()) } returns Login.Response(null)

        handler.execute(Command("login", listOf("@alice", "1234")))

        assertThat(output.lines).endsWith("ERROR: Invalid credentials for @alice")
    }


    @Test
    fun `login fails if username is empty`() {
        handler.execute(Command("login", listOf("", "1234")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { login wasNot Called }
    }

    @Test
    fun `login fails if password is empty`() {
        handler.execute(Command("login", listOf("@alice", "")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { login wasNot Called }
    }

    @Test
    fun `login fails if username not start with @`() {
        handler.execute(Command("login", listOf("alice", "1234")))

        assertThat(output.lines).endsWith("ERROR: username must start with @")
        verify { login wasNot Called }
    }

    @Test
    fun `login fails if username is not at least 4 characters long`() {
        handler.execute(Command("login", listOf("@a", "1234")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { login wasNot Called }
    }

    @Test
    fun `login fails if password is not at least 4 characters long`() {
        handler.execute(Command("login", listOf("@alice", "123")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { login wasNot Called }
    }

    @BeforeEach
    fun setup() {
        every { core.login() } returns login
    }

    private val output = FakeOutput()
    private val login = mockk<Login>(relaxed = true)
    private val core = mockk<Core>(relaxed = true)
    private val input = InputStub("", output)
    private val console = ConsoleApp(input, output)
    private val handler = LoginHandler(output, core, console.session)
}
