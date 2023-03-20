

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.EmptyPostError
import com.proyecto404.finalProjectJP.core.domain.exceptions.PostMaxedLengthError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotAuthenticatedError
import com.proyecto404.finalProjectJP.core.domain.services.AuthService
import com.proyecto404.finalProjectJP.core.domain.services.SessionToken
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryPosts
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.Post
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.proyecto404.finalProjectJP.core.domain.Post as UserPost

class PostTest {
    @Test
    fun `post creates a new post`() {
        post.exec(Post.Request("@alice", aToken, "What a beautiful day!"))

        assertThat(posts.get("@alice")).isEqualTo(listOf(UserPost(1, "@alice", "What a beautiful day!")))
    }

    @Test
    fun `users must be authenticated to post`() {
        alice.addToken(otherToken)

        assertThrows<UserNotAuthenticatedError> {
            post.exec(Post.Request("@alice", aToken, "What a beautiful day!"))
        }
    }

    @Test
    fun `post must not be empty to be created`() {
        assertThrows<EmptyPostError> {
            post.exec(Post.Request("@alice", aToken, ""))
        }
    }

    @Test
    fun `posts cannot have more than 140 characters`() {
        assertThrows<PostMaxedLengthError> {
            post.exec(Post.Request("@alice", aToken, aReallyLongPost))
        }
    }

    @BeforeEach
    fun setUp() {
        every { users.get(any()) } returns alice
        alice.addToken(aToken)
    }

    private val otherToken = SessionToken("otherToken")
    private val aToken = SessionToken("aToken")
    private val alice = User(1, "@alice", "1234")
    private val posts = InMemoryPosts()
    private val users = mockk<InMemoryUsers>()
    private val auth = AuthService()
    private val post = Post(posts, users, auth)
    private val aReallyLongPost = "This post has more than 140 characters. It shouldn't be allowed to be post. This post has more than 140 characters. It shouldn't be allowed to be post"
}
