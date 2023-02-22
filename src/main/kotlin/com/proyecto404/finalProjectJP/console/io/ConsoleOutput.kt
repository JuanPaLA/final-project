package com.proyecto404.finalProjectJP.console.io

class ConsoleOutput: Output {
    var contents = ""
    override fun println(text: String) {
        System.out.println(text)
        contents += text
    }
}