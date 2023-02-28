package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandHandler
import com.proyecto404.finalProjectJP.console.commandProcessor.CommandProcessor
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.io.InputStub
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CommandProcessorTest {
    @Test
    fun `exit stops processing`() {
        input.willRead("exit")

        processor.run()

        assertThat(output.lines).containsExactly(
            "> exit",
            "bye bye",
        )
    }

    @Test
    fun `unknown command`() {
        input.willReadAndExit("unknown")

        processor.run()

        assertThat(output.lines).containsSequence(
            "> unknown",
            "ERROR: Invalid command unknown",
        )
    }

    @Test
    fun `executes known command`() {
        handlers.add(CommandPrinterHandler("some-command", output))
        input.willReadAndExit("some-command")

        processor.run()

        assertThat(output.lines).containsSequence(
            "> some-command",
            "some-command",
        )
    }

    @Test
    fun `multiple commands`() {
        handlers.add(CommandPrinterHandler("some-command", output))
        handlers.add(CommandPrinterHandler("other-command", output))
        input.willReadAndExit("some-command", "other-command")

        processor.run()

        assertThat(output.lines).containsSequence(
            "> some-command",
            "some-command",
            "> other-command",
            "other-command",
        )
    }

    @Test
    fun `pass command's arguments to handlers`() {
        handlers.add(CommandPrinterHandler("some-command", output))
        input.willReadAndExit("some-command param1 param2")

        processor.run()

        assertThat(output.lines).containsSequence(
            "> some-command param1 param2",
            "some-command param1 param2",
        )
    }

    private val output = FakeOutput()
    private val input = InputStub("", output)
    private val handlers = mutableListOf<CommandHandler>()
    private val sessionState = SessionState()
    private val promptPrinter = SessionPrinter(sessionState)
    private val processor by lazy { CommandProcessor(input, output, handlers, promptPrinter) }

    class CommandPrinterHandler(override val name: String, private val output: Output): CommandHandler {
        override fun execute(command: Command) {
            output.print(command.name)
            if (command.args.isNotEmpty()) output.print(" " + command.args.joinToString(" "))
            output.print("\n")
        }
    }
}
