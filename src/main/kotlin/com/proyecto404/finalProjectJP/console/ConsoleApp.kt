package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commandProcessor.CommandProcessor
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.LoginHandler
import com.proyecto404.finalProjectJP.console.commandProcessor.handlers.SignUpHandler
import com.proyecto404.finalProjectJP.console.io.Input
import com.proyecto404.finalProjectJP.console.io.Output
import com.proyecto404.finalProjectJP.core.Core

class ConsoleApp(input: Input, output: Output) {
    private val core = Core()
    private val handlers = listOf(SignUpHandler(output, core), LoginHandler(output, core))
    private val processor = CommandProcessor(input, output, handlers)

    fun run() {
        processor.run()
    }
}
