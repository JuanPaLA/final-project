package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.LoginHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.core.useCases.Login
import com.proyecto404.finalProjectJP.core.useCases.Login.Request
import com.proyecto404.finalProjectJP.core.useCases.Login.Response
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LoginHandlerTest {
    @Test
    fun `valid request`() {
        handler.execute(validCommand)

        verify {
            useCase.exec(Request(validCommand.args[0], validCommand.args[1]))
        }
    }

    @Test
    fun `valid response`() {
        every { useCase.exec(Request(validCommand.args[0],validCommand.args[1])) } returns Response(true)

        handler.execute(validCommand)

        assertThat(output.lines).containsSequence(
            "login @alice 1234",
            "Logged in as @alice!",
        )
    }

    @Test
    fun `invalid request`() {
        every { useCase.exec(any()) } returns Response(false)

        handler.execute(invalidCommand)

        assertThat(output.lines).containsSequence(
            "login ${invalidCommand.args[0]} ${invalidCommand.args[1]}",
            "ERROR: Invalid credentials for ${invalidCommand.args[0]}"
        )
    }

    private val output = FakeOutput()
    private val validCommand = Command("login", listOf("@alice", "1234"))
    private val invalidCommand = Command("login", listOf("@alice", ""))
    private val useCase = mockk<Login>(relaxed = true)
    private val handler = LoginHandler("login", output, useCase)
}
