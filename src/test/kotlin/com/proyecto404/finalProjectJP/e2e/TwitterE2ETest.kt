package com.proyecto404.finalProjectJP.e2e

import com.proyecto404.finalProjectJP.console.Console
import com.proyecto404.finalProjectJP.console.io.InputStub
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TwitterE2ETest {
    @Test
    fun `successful login`() {
        input.willRead("$ALICE_LOGIN_INPUT")

        console.run()

        assertThat(output.contents).endsWith("Logged in as $ALICE_USERNAME!")
    }

    private val ALICE_LOGIN_INPUT = "login @alice 777"
    private val ALICE_USERNAME = "@alice"
    private val output = FakeOutput()
    private val input = InputStub("$ALICE_LOGIN_INPUT ", output)
    private val console = Console(input, output)
}