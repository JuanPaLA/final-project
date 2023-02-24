package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.InvalidLoginCredentialsError
import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.LoginHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.Login
import com.proyecto404.finalProjectJP.core.useCases.Login.Request
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LoginHandlerTest {
    @Test
    fun `do signup with given username and password`() {
        handler.execute(Command("login", listOf("@alice", "1234")))

        verify {
            login.exec(Request("@alice", "1234"))
        }
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
    private val handler = LoginHandler(output, core)
}
