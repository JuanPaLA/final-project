package com.proyecto404.finalProjectJP.console.commandProcessor

class Command(val name: String, val args: List<String>){
    companion object {
        fun fromString(commandString: String): Command {
            val parts = commandString.split(" ")
            val name = parts.first()
            val args = parts.drop(1)
            return Command(name, args)
        }
    }
}