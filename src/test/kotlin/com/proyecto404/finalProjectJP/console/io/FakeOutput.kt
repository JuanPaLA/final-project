package com.proyecto404.finalProjectJP.console.io

class FakeOutput: Output {
    var contents = ""

    override fun println(message: String) {
        contents += message + "\n"
    }

    fun add(content: String){
        contents += "> $content\n"
    }
}