package com.proyecto404.finalProjectJP.e2e

import com.proyecto404.finalProjectJP.console.ConsoleApp
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.io.InputStub
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TwitterE2ETest {
    @Test
    fun login() {
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> exit",
            "bye bye",
        )
    }

    @Test
    fun logout() {
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("logout")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> logout",
            "Logged out!",
            "> exit",
            "bye bye",
        )
    }

    private val output = FakeOutput()
    private val input = InputStub("", output)
    private val console = ConsoleApp(input, output)
}