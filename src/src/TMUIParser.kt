package src

import java.io.File
/*have been seen parsing user input including
 *"in" files and maybe console input*/


class TMUIParser {


    fun parseStates(inputFile:String):MutableList<State> {
        val regex = """\d+\s->\s[(\s.[<.>]\d+)|.+]""".toRegex()         // \d+\s->\s[(\s.[<.>]\d+)|.+]+
        var states:MutableList<State> = mutableListOf()                        //8 -> h>8 noCap s>1 _.0
                                                                               //0     1    2      3
        val lines:List<String> = File(inputFile).readLines()

        for (line in lines) {
            if(!regex.matches(line)){
                print("Please write states in a correct format. E.g.: 8 -> h>8 nonSpec s>1 _.0")
                System.exit(1)                              // 0     1       2      3    4
            }

            val parsedLine = line.split(" ", "->")

            var number = Integer.parseInt(parsedLine[0])
            var symbols:MutableList<String> = mutableListOf()
            var motion:MutableList<Command> = mutableListOf()
            var nextStates:MutableList<Int> = mutableListOf()


            for (i in 1 until parsedLine.size) {

                if(parsedLine[i] == "nonSpec") {
                    symbols.add("nonSpec")
                    motion.add(Command.IllegalCommand)
                    nextStates.add(0)
                    continue
                }

                symbols.add(parsedLine[i].get(0)+"")
                motion.add(
                    when(parsedLine[i].get(1)+"") {
                        "<" -> Command.Left
                        ">" -> Command.Right
                        "." -> Command.NoMove
                        else -> Command.IllegalCommand
                    })
                nextStates.add(Integer.parseInt(parsedLine[i].substring(2)))
            }

            var state = State(number, symbols, motion, nextStates)
            states.add(state)
        }
        return states
    }



    fun parseTape(inputFile:String):MutableList<String> {
        var tape:MutableList<String> = mutableListOf()

        return tape
    }


    fun parseAlphabet(inputFile:String):String = File(inputFile).readLines().toString()

}