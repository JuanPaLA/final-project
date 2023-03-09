package com.proyecto404.finalProjectJP.e2e.consoleApp

import com.proyecto404.finalProjectJP.console.ConsoleApp
import com.proyecto404.finalProjectJP.console.io.FakeOutput
import com.proyecto404.finalProjectJP.console.io.InputStub
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TwitterE2ETest {
    @Test
    fun login() {
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> exit",
            "bye bye",
        )
    }

    @Test
    fun logout() {
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("logout")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> logout",
            "Logged out!",
            "> exit",
            "bye bye",
        )
    }

    @Test
    fun post() {
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("post What a beautiful day!")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> post What a beautiful day!",
            "@alice> exit",
            "bye bye",
        )
    }

    @Test
    fun read() {
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("post What a beautiful day!")
        input.willRead("read @alice")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> post What a beautiful day!",
            "@alice> read @alice",
            "- What a beautiful day! (0 minutes ago)",
            "@alice> exit",
            "bye bye",
        )
    }

    @Test
    fun `follow and wall`() {
        input.willRead("signup @bob 1234")
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("post What a beautiful day!")
        input.willRead("login @bob 1234")
        input.willRead("follow @alice")
        input.willRead("wall")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @bob 1234",
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> post What a beautiful day!",
            "@alice> login @bob 1234",
            "Logged in as @bob!",
            "@bob> follow @alice",
            "@bob> wall",
            "- @alice: What a beautiful day! (0 minutes ago)",
            "@bob> exit",
            "bye bye",
        )
    }

    @Test
    fun `following and followers`() {
        input.willRead("signup @bob 1234")
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("follow @bob")
        input.willRead("followers @bob")
        input.willRead("following @alice")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @bob 1234",
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> follow @bob",
            "@alice> followers @bob",
            "- @alice",
            "@alice> following @alice",
            "- @bob",
            "@alice> exit",
            "bye bye",
        )
    }

    @Test
    fun unfollow() {
        input.willRead("signup @bob 1234")
        input.willRead("signup @alice 1234")
        input.willRead("login @alice 1234")
        input.willRead("post What a beautiful day!")
        input.willRead("login @bob 1234")
        input.willRead("follow @alice")
        input.willRead("wall")
        input.willRead("unfollow @alice")
        input.willRead("wall")
        input.willRead("exit")

        console.run()

        assertThat(output.lines).containsExactly(
            "> signup @bob 1234",
            "> signup @alice 1234",
            "> login @alice 1234",
            "Logged in as @alice!",
            "@alice> post What a beautiful day!",
            "@alice> login @bob 1234",
            "Logged in as @bob!",
            "@bob> follow @alice",
            "@bob> wall",
            "- @alice: What a beautiful day! (0 minutes ago)",
            "@bob> unfollow @alice",
            "@bob> wall",
            "ERROR: No posts in your timeline",
            "@bob> exit",
            "bye bye",
        )
    }

    private val output = FakeOutput()
    private val input = InputStub("", output)
    private val console = ConsoleApp(input, output)
}
