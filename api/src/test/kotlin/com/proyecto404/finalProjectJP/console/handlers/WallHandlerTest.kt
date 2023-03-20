package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.WallHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.console.session.User
import com.proyecto404.finalProjectJP.console.session.exceptions.NotLoggedInError
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Wall
import com.proyecto404.finalProjectJP.core.useCases.Wall.Request
import com.proyecto404.finalProjectJP.core.useCases.Wall.Response
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.proyecto404.finalProjectJP.core.domain.User as UserEntity

class WallHandlerTest {
    @Test
    fun `wall request timeline of given user`() {
        handler.execute(Command("wall", listOf()))

        verify {
            wall.exec(Request("@alice", aToken))
        }
    }

    @Test
    fun `wall prints user's timeline posts`() {
        every { wall.exec(any()) } returns Response(listOf(
            Post(0, "@bob", "Hello, world!"),
            Post(1, "@bob", "Learning python!")
        ))

        handler.execute(Command("wall", listOf()))

        assertThat(output.lines).containsSequence(
            "- @bob: Hello, world! (0 minutes ago)",
            "- @bob: Learning python! (0 minutes ago)",
        )
    }

    @Test
    fun `users must be logged in to request wall timeline`() {
        session.logout()

        assertThrows<NotLoggedInError> {
            wall.exec(Request("@alice", session.getSession().token))
        }
    }

    @BeforeEach
    fun setup() {
        every { core.wall() } returns wall
        users.add(UserEntity(1, "@alice", "1234"))
        session.authenticate(User("@alice", aToken))
    }

    private val users = InMemoryUsers()
    private val aToken = SessionToken("aToken")
    private val output = FakeOutput()
    private val core = mockk<Core>()
    private val session = SessionState()
    private val handler = WallHandler(output, core, session)
    private val wall = mockk<Wall>(relaxed = true)
}
