package com.proyecto404.finalProjectJP.console.commands

class Command(val name: String, val args: List<String>) {
    companion object{
        fun fromString(commandString: String): Command {
            val parts = commandString.split(" ")
            val name = parts.first()
            val args = parts.drop(1)
            return Command(name, args)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Command) return false

        if (name != other.name) return false
        if (args != other.args) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + args.hashCode()
        return result
    }

    override fun toString(): String {
        return "Command(name='$name', args=$args)"
    }


}
