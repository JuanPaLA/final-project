package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commandProcessor.CommandProcessor
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.*
import com.proyecto404.finalProjectJP.console.io.Input
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.console.session.SessionPrinter
import com.proyecto404.finalProjectJP.console.session.SessionState
import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers

class ConsoleApp(input: Input, output: Output) {
    var session = SessionState()
    private val prompt = SessionPrinter(session)
    private val core = Core(Core.Configuration(InMemoryUsers()))
    private val handlers = listOf(
        SignUpHandler(output, core),
        LoginHandler(output, core, session),
        LogoutHandler(output, core, session),
        PostHandler(output, core, session),
        ReadHandler(output, core, session),
        FollowHandler(output, core, session),
        WallHandler(output, core, session),
        FollowingHandler(output, core, session),
        FollowerHandler(output, core, session),
        UnfollowHandler(output, core, session)
    )

    private val processor = CommandProcessor(input, output, handlers, prompt)

    fun run() {
        processor.run()
    }
}
