package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.InvalidSignUpError
import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.SignUpHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.useCases.SignUp
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SignUpHandlerTest {
    @Test
    fun `handler invokes SignUp with correct parameters`() {
        handler.execute(Command("signup", listOf("@alice", "1234")))

        verify {
            useCase.exec(SignUp.Request("@alice", "1234"))
        }
    }

    @ParameterizedTest
    @CsvSource(
        "alice, 1234",
        "alice, 1234",
        "@alice, ''",
        "@,1234",
        "@al, 123",
        "@al,''"
    )
    fun `user must signup with @name and password`(userName: String, password: String) {
        assertThrows<InvalidSignUpError> {
            handler.execute(Command("signup", listOf(userName, password)))
        }
    }

    @BeforeEach
    fun setup() {
        every { core.signup() } returns useCase
    }

    private val output = FakeOutput()
    private val useCase = mockk<SignUp>(relaxed = true)
    private val core = mockk<Core>(relaxed = true)
    private val handler = SignUpHandler(output, core)
}
