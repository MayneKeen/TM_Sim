package src

import java.io.File
import java.lang.IllegalArgumentException


/*have been seen parsing user input including
 *"in" files and maybe console input*/


class TMUIParser {
    constructor()

    fun parseStates(inputFile: String, alphabet:String): MutableList<State> {
        val states: MutableList<State> = mutableListOf()
        val lines: List<String> = File(inputFile).readLines()
        val regex = """\d+\s->\s(.\s=\s(.[<\.>]\d+\s)|(.+))+""".toRegex()

        for (line in lines) {
            for (line in lines) {
                if (!regex.matches(line)) {
                    throw IllegalArgumentException(
                        "Please write states in a correct format. E.g.: 8 -> a=h>8 b=nonSpec c=s>1 x=_.0 " +
                                "incorrect line format has been found at ${line[0]} state"
                    )
                    System.exit(1)
                }
            }
        }

        for (line in lines) {
            val parsedLine = line.split(" -> ", " ")

            val number = Integer.parseInt(parsedLine[0])
            var symbols: MutableList<Char> = mutableListOf()
            var motion: MutableList<Command> = mutableListOf()
            var nextStates: MutableList<Int> = mutableListOf()

            for(i in 1 until parsedLine.size) {
                val part = parsedLine[i]
                val tapeSym = part[0]
                val index = alphabet.indexOf(tapeSym)


                if(part.contains("nonSpec")) {
                    symbols.add(index, ' ')
                    motion.add(index, Command.IllegalCommand)
                    nextStates.add(index, -1)
                    continue
                }

                val sym = part[2]
                val command =
                    when (part[3]) {
                        '<' -> Command.Left
                        '>' -> Command.Right
                        '.' -> Command.NoMove
                        else -> Command.IllegalCommand
                    }
                val next = Integer.parseInt(part.substring(4))

                symbols.add(index, sym)
                motion.add(index, command)
                nextStates.add(index, next)


            }
            val state = State(number, symbols, motion, nextStates)
            states.add(state)
        }
        return states
    }
        fun parseTape(inputFile: String): String {
            var list = File(inputFile).readLines()
            return list.joinToString()
        }
        fun parseAlphabet(inputFile: String): String {
            var list = File(inputFile).readLines()
            return list.joinToString()
        }
}