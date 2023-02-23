package com.proyecto404.finalProjectJP.console.io

class ConsoleOutput: Output {
    override fun println(text: String) {
        System.out.println(text)
    }

    override fun print(text: String) {
        System.out.print(text)
    }
}