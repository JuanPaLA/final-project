package com.proyecto404.finalProjectJP.console.io

class ConsoleInput: Input {
    override fun readln() = readLine() ?: ""
}