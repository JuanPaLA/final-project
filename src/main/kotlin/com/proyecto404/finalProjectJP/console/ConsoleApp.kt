package com.proyecto404.finalProjectJP.console

import com.proyecto404.finalProjectJP.console.commandProcessor.CommandProcessor
import com.proyecto404.finalProjectJP.console.io.Input
import com.proyecto404.finalProjectJP.console.io.Output

class ConsoleApp(input: Input, output: Output) {
    private val processor = CommandProcessor(input, output, listOf())

    fun run() {
        processor.run()
    }
}
