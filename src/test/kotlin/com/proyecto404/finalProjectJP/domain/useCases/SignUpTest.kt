package com.proyecto404.finalProjectJP.domain.useCases
import org.assertj.core.api.Assertions.assertThat

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.Users
import com.proyecto404.finalProjectJP.core.domain.useCases.SignUp
import com.proyecto404.finalProjectJP.core.domain.useCases.SignUp.Request
import com.proyecto404.finalProjectJP.core.domain.useCases.SignUp.Response
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class SignUpTest {
    @Test
    fun `valid signup returns successful response`() {
        val response = signup.exec(Request("@alice", "1234"))

        assertThat(response).isEqualTo(Response(true, "successful signup"))
    }

    @Test
    fun `signup adds a new user to repository`() {
        signup.exec(Request("@alice", "1234"))

        verify { users.add(User("@alice", "1234")) }
    }

    @Test
    fun `signup cannot add repeated usernames`() {
        every { users.get("@alice") } returns User("@alice", "1234")

        val response = signup.exec(Request("@alice", "1234"))

        assertThat(response).isEqualTo(Response(false, "ERROR: User @alice already exists"))
    }

    private val users = mockk<Users>(relaxed = true)
    private val signup = SignUp(users)
}
