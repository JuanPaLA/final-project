package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commands.Command
import com.proyecto404.finalProjectJP.console.io.ConsoleOutput
import com.proyecto404.finalProjectJP.console.io.InputStub
import com.proyecto404.finalProjectJP.core.Core
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConsoleTest {

    @Test
    fun `reads login command`() {
        val command = console.readCommand()

        assertThat(command).isEqualTo(LOGIN_COMMAND)
    }

    @Test
    fun `executes login command`() {
        console.run()

        verify { console.execute(LOGIN_COMMAND) }
    }

    private val mockCore = mockk<Core>(relaxed = true)
    private val LOGIN_COMMAND = Command("login", listOf<String>("@alice", "777"))
    private val ALICE_LOGIN_INPUT = "login @alice 777"
    private val output = ConsoleOutput()
    private val input = InputStub("$ALICE_LOGIN_INPUT")
    private val console = Console(input, output)
}