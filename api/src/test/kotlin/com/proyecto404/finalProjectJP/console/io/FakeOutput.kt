package com.proyecto404.finalProjectJP.console.io

class FakeOutput: Output {
    var contents = ""
    val lines get() = contents.lines().dropLast(1)

    override fun println(text: String) {
        print(text)
        print("\n")
    }

    override fun print(text: String) {
        contents += text
    }
}