package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.SignUpHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.SignUp
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SignUpHandlerTest {
    @Test
    fun `do signup with given username and password`() {
        handler.execute(Command("signup", listOf("@alice", "1234")))

        verify {
            signup.exec(SignUp.Request("@alice", "1234"))
        }
    }

    @Test
    fun `signup fails if username is empty`() {
        handler.execute(Command("signup", listOf("", "1234")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { signup wasNot Called }
    }

    @Test
    fun `signup fails if password is empty`() {
        handler.execute(Command("signup", listOf("@alice", "")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { signup wasNot Called }
    }

    @Test
    fun `signup fails if username not start with @`() {
        handler.execute(Command("signup", listOf("alice", "1234")))

        assertThat(output.lines).endsWith("ERROR: username must start with @")
        verify { signup wasNot Called }
    }

    @Test
    fun `signup fails if username is not at least 4 characters long`() {
        handler.execute(Command("signup", listOf("@al", "1234")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { signup wasNot Called }
    }

    @Test
    fun `signup fails if password is not at least 4 characters long`() {
        handler.execute(Command("signup", listOf("@alice", "123")))

        assertThat(output.lines).endsWith("ERROR: username and password must be at least 4 characters long")
        verify { signup wasNot Called }
    }

    @BeforeEach
    fun setup() {
        every { core.signup() } returns signup
    }

    private val output = FakeOutput()
    private val signup = mockk<SignUp>(relaxed = true)
    private val core = mockk<Core>()
    private val handler = SignUpHandler(output, core)
}
