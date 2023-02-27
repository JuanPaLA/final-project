package com.proyecto404.finalProjectJP.core.useCases

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidPasswordError
import com.proyecto404.finalProjectJP.core.domain.exceptions.InvalidUsernameError
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.core.useCases.SignUp.Request
import com.proyecto404.finalProjectJP.core.domain.exceptions.RepeatedUsernameError
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SignUpTest {
    @Test
    fun `signup creates new user`() {
        signup.exec(Request("@alice", "1234"))

        assertThat(users.get("@alice")).isEqualTo(User("@alice", "1234"))
    }

    @Test
    fun `usernames must be unique`() {
        signup.exec(Request("@alice", "1234"))

        assertThrows<RepeatedUsernameError> {
            signup.exec(Request("@alice", "1234"))
        }
    }

    @Test
    fun `username must not be empty`() {
        assertThrows<InvalidUsernameError> {
            signup.exec(Request("", "1234"))
        }
    }

    @Test
    fun `usernames start with @`() {
        assertThrows<InvalidUsernameError> {
            signup.exec(Request("alice", "1234"))
        }
    }

    @Test
    fun `usernames have at least 4 characters long`() {
        assertThrows<InvalidUsernameError> {
            signup.exec(Request("@al", "1234"))
        }
    }

    @Test
    fun `password must not be empty`() {
        assertThrows<InvalidPasswordError> {
            signup.exec(Request("@alice", ""))
        }
    }

    @Test
    fun `password have at least 4 characters long`() {
        assertThrows<InvalidPasswordError> {
            signup.exec(Request("@alice", "123"))
        }
    }

    private val users = InMemoryUsers()
    private val signup = SignUp(users)
}
