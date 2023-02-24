package com.proyecto404.finalProjectJP.infraestructure.persistance.InMemory

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.inMemory.InMemoryUsers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InMemoryUsersTest {
    @Test
    fun `add creates a new user`() {
        users.add(User("@alice", "1234"))

        assertThat(users.get("@alice")).isEqualTo(User("@alice", "1234"))
    }

    @Test
    fun `get user returns the matched user with precised username`() {
        users.add(alice)

        assertThat(alice).isEqualTo(users.get("@alice"))
    }

    private val users = InMemoryUsers()
    private val alice = User("@alice", "1234")
}
