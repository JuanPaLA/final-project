package com.proyecto404.finalProjectJP.core.infraestructure.persistance.InMemory

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.RepeatedUsernameError
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InMemoryUsersTest {
    @Test
    fun `add creates a new user`() {
        users.add(User("@alice", "1234"))

        assertThat(users.get("@alice")).isEqualTo(User("@alice", "1234"))
    }

    @Test
    fun `users with repeated username can not be created`() {
        users.add(User("@alice", "1234"))

        assertThrows<RepeatedUsernameError> {
            users.add(User("@alice", "1234"))
        }
    }

    @Test
    fun `get returns the matched user with given username`() {
        users.add(alice)

        assertThat(alice).isEqualTo(users.get("@alice"))
    }

    @Test
    fun `delete removes given user`() {
        users.add(alice)
        users.delete(alice.name)

        assertThrows<UserNotFoundError> {
            users.get(alice.name)
        }
    }

    @Test
    fun `updates change user properties`() {
        users.add(User("@alice", "1234"))
        users.update(User("@alice", "4444"))

        val newUser = users.get("@alice")

        assertThat(newUser).isEqualTo(User("@alice", "4444"))
    }

    private val users = InMemoryUsers()
    private val alice = User("@alice", "1234")
}
