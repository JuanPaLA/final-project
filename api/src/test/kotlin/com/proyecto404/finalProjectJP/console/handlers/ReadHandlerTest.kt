package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.ReadHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.console.session.User
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Read
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReadHandlerTest {
    @Test
    fun `read requests posts from given username`() {
        handler.execute(Command("read", listOf("@bob")))

        verify {
            read.exec(Read.Request("@alice", "@bob", aToken))
        }
    }

    @Test
    fun `successful read prints list of posts`() {
        every { read.exec(any()) } returns Read.Response(listOf(
            Post(1, "@bob", "Good game though."),
            Post(1, "@bob", "Damn! We lost!")
        ))

        handler.execute(Command("read", listOf("@bob")))

        assertThat(output.lines).containsSequence(
            "- Good game though. (0 minutes ago)",
            "- Damn! We lost! (0 minutes ago)"
        )
    }

    @Test
    fun `to request read posts an author's name must be given `() {
        handler.execute(Command("read", listOf()))

        assertThat(output.lines).endsWith("ERROR: Must specify an user")
        verify { read wasNot Called }
    }

    @Test
    fun `users must be logged in to read posts`() {
        session.logout()

        handler.execute(Command("read", listOf("@bob")))

        assertThat(output.lines).endsWith("ERROR: User must be logged to post messages")
        verify { read wasNot Called }
    }

    @Test
    fun `user is alerted when no existing posts to read`() {
        every { read.exec(any()) } returns Read.Response(listOf())

        handler.execute(Command("read", listOf("@bob")))

        assertThat(output.lines).endsWith("ERROR: There are no post from @bob")
    }

    @BeforeEach
    fun setup() {
        every { core.read() } returns read
        session.authenticate(User("@alice", aToken))
    }

    private val aToken = SessionToken("aToken")
    private val output = FakeOutput()
    private val read = mockk<Read>(relaxed = true)
    private val core = mockk<Core>(relaxed = true)
    private val session = SessionState()
    private val handler = ReadHandler(output, core, session)
}