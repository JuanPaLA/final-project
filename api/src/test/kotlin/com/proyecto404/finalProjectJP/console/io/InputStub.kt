package com.proyecto404.finalProjectJP.console.io

class InputStub(private val defaultInput: String = "", private val output: Output): Input {
    private val nextInputs = mutableListOf<String>()
    private var currentIndex = 0

    override fun readln(): String {
        val stringOutput = nextInputs.getOrNull(currentIndex++) ?: defaultInput
        output.println(stringOutput)
        return stringOutput
    }

    fun willRead(vararg inputs: String) {
        nextInputs.addAll(inputs)
    }

    fun willReadAndExit(vararg inputs: String) {
        willRead(*inputs)
        willRead("exit")
    }
}





