package com.proyecto404.finalProjectJP.e2e

import com.proyecto404.finalProjectJP.console.ConsoleApp
import com.proyecto404.finalProjectJP.console.io.InputStub
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TwitterE2ETest {
    @Test
    fun login() {
        input.willRead("signup @alice 777")
        input.willRead("login @alice 777")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 777",
            "> login @alice 777",
            "Logged in as @alice",
            "@alice> exit",
            "bye bye",
        )
    }

    private val output = FakeOutput()
    private val input = InputStub("", output)
    private val console = ConsoleApp(input, output)
}