package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.FollowerHandler
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.FollowingHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Followers
import com.proyecto404.finalProjectJP.core.useCases.Following
import com.proyecto404.finalProjectJP.core.useCases.Following.*
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.proyecto404.finalProjectJP.console.session.User as UserEntity
import com.proyecto404.finalProjectJP.core.useCases.Followers.Request as FollowersRequest

class FollowingHandlerTest {
    @Test
    fun `do following request of given user`() {
        followingHandler.execute(Command("following", listOf("@bob")))

        verify {
            following.exec(Request("@alice", "@bob", aToken))
        }
    }

    @Test
    fun `do followers request of given user`() {
        followersHandler.execute(Command("followers", listOf("@bob")))

        verify {
            followers.exec(FollowersRequest("@alice", "@bob", aToken))
        }
    }

    @Test
    fun `followings returns a list of user's names`() {
        every { following.exec(any()) } returns Response(listOf("@juan", "@bob"))

        followingHandler.execute(Command("following", listOf("@alice")))

        assertThat(output.lines).containsSequence(
            "- @juan",
            "- @bob",
        )
    }

    @Test
    fun `followers returns a list of user's names`() {
        every { followers.exec(any()) } returns Followers.Response(listOf("@alice"))

        followersHandler.execute(Command("followers", listOf("@bob")))

        assertThat(output.lines).containsSequence(
            "- @alice",
        )
    }

    @Test
    fun `following username must be given to request list of following users`() {
        followingHandler.execute(Command("following", listOf()))

        assertThat(output.lines).endsWith("ERROR: A following username must be given")
    }

    @Test
    fun `followers username must be given to request list of followers users`() {
        followersHandler.execute(Command("followers", listOf()))

        assertThat(output.lines).endsWith("ERROR: A followers username must be given")
    }

    @Test
    fun `users must be logged in to request following`() {
        session.logout()

        followingHandler.execute(Command("following", listOf("@alice")))

        assertThat(output.lines).endsWith("ERROR: User must be logged in to follow users")
        verify { following wasNot Called }
    }

    @Test
    fun `users must be logged in to request followers`() {
        session.logout()

        followersHandler.execute(Command("followers", listOf("@alice")))

        assertThat(output.lines).endsWith("ERROR: User must be logged in to follow users")
        verify { followers wasNot Called }
    }

    @BeforeEach
    fun setup() {
        every { core.following() } returns following
        every { core.followers() } returns followers
        session.authenticate(UserEntity("@alice", aToken))
    }

    private val aToken = SessionToken("aToken")
    private val output = FakeOutput()
    private val core = mockk<Core>()
    private val session = SessionState()
    private val followingHandler = FollowingHandler(output, core, session)
    private val followersHandler = FollowerHandler(output, core, session)
    private val following = mockk<Following>(relaxed = true)
    private val followers = mockk<Followers>(relaxed = true)
}
