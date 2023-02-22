package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commands.Command
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.io.InputStub
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConsoleTest {
    @Test
    fun `reads valid login command from prompt`() {
        console.run()

        assertThat(output.contents).isEqualTo("> $aliceLoginInput\n $loginMessage")
    }

    @Test
    fun `process valid login command`() {
        console.run()

        val command = console.readCommand()

        assertThat(command).isEqualTo(loginCommand)
    }


    @Test
    fun `executes inserted command after run`() {
        consoleSpy.run()

        verifyOrder {
            consoleSpy.readCommand()
            consoleSpy.execute(loginCommand)
        }
    }

    private val loginMessage = "Logged in as @alice!"
    private val loginCommand = Command("login", listOf<String>("@alice", "777"))
    private val aliceLoginInput = "login @alice 777"
    private val output = FakeOutput()
    private val input = InputStub("$aliceLoginInput", output)
    private val consoleSpy = spyk(Console(input, output))
    private val console = Console(input, output)
}