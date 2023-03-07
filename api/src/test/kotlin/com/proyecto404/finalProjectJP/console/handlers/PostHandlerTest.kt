package com.proyecto404.finalProjectJP.console.handlers

import com.proyecto404.finalProjectJP.console.commandProcessor.Command
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.PostHandler
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.console.session.User
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.useCases.Post
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostHandlerTest {
    @Test
    fun `do post with given username and content`() {
        handler.execute(Command("post", listOf("what incredible day!")))

        verify {
            post.exec(Post.Request("@alice", aToken, "what incredible day!"))
        }
    }

    @Test
    fun `post's content must not be empty to be created`() {
        handler.execute(Command("post", listOf("")))

        assertThat(output.lines).endsWith("ERROR: Empty message")
        verify { post wasNot Called }
    }

    @Test
    fun `users must be logged in to post`() {
        every { session.isAuthenticated() } returns false

        handler.execute(Command("post", listOf("What an incredible day!")))

        assertThat(output.lines).endsWith("ERROR: User must be logged to post messages")
        verify { post wasNot Called }
    }

    @Test
    fun `post can have up to 140 characters`() {
        handler.execute(Command("post", listOf(aReallyLongPost)))

        assertThat(output.lines).endsWith("ERROR: Post can't be longer than 140 characters")
        verify { post wasNot Called }
    }

    @BeforeEach
    fun setup() {
        every { session.isAuthenticated() } returns true
        every { session.getSession() } returns User("@alice", SessionToken("aToken"))
        every { core.post() } returns post
    }

    private val aToken = SessionToken("aToken")
    private val output = FakeOutput()
    private val post = mockk<Post>(relaxed = true)
    private val core = mockk<Core>(relaxed = true)
    private val session = mockk<SessionState>(relaxed = true)
    private val handler = PostHandler(output, core, session)
    private val aReallyLongPost = "This post has more than 140 characters. It shouldn't be allowed to be post. This post has more than 140 characters. It shouldn't be allowed to be post"
}
