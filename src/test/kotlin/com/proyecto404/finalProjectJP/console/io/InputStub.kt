package com.proyecto404.finalProjectJP.console.io

class InputStub(private val defaultInput: String = "", private val output: FakeOutput = FakeOutput()): Input {
    private val nextInputs = mutableListOf<String>()
    private var currentIndex = 0

    override fun readln(): String {
        val stringOutput = nextInputs.getOrNull(currentIndex++) ?: defaultInput
        output.add(stringOutput)
        return stringOutput
    }

    fun willRead(input: String) {
        nextInputs.add(input)
    }
}





