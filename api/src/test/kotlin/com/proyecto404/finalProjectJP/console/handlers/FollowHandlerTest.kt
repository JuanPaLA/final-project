package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.FollowHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.console.session.User
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Follow
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FollowHandlerTest {
    @Test
    fun `given user follows given user`() {
        handler.execute(Command("follow", listOf("@bob")))

        verify {
            follow.exec(Follow.Request("@alice", "@bob", aToken))
        }
    }

    @Test
    fun `users must be logged in to follow other users`() {
        session.logout()

        handler.execute(Command("follow", listOf("@alice", "@bob")))

        assertThat(output.lines).endsWith("ERROR: User must be logged in to follow other users")
    }

    @Test
    fun `followed username must be given and not empty`() {
        handler.execute(Command("follow", listOf()))

        assertThat(output.lines).endsWith("ERROR: a username to follow must be provided")
        verify { follow wasNot Called }
    }

    @BeforeEach
    fun setup() {
        every { core.follow() } returns follow
        session.authenticate(User("@alice", aToken))
    }

    private val aToken = SessionToken("aToken")
    private val output = FakeOutput()
    private val follow = mockk<Follow>(relaxed = true)
    private val core = mockk<Core>(relaxed = true)
    private val session = SessionState()
    private val handler = FollowHandler(output, core, session)
}
