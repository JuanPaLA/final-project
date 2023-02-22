package com.proyecto404.finalProjectJP.e2e

import com.proyecto404.finalProjectJP.console.Console
import com.proyecto404.finalProjectJP.console.io.InputStub
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TwitterE2ETest {
    @Test
    fun `successful login`() {
        console.run()

        assertThat(output.contents).isEqualTo("> $aliceLoginInput\n $loginMessage")
    }
    private val loginMessage = "Logged in as @alice!"
    private val aliceLoginInput = "login @alice 777"
    private val output = FakeOutput()
    private val input = InputStub("$aliceLoginInput ", output)
    private val console = Console(input, output)
}