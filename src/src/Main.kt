package src

import org.junit.jupiter.api.Test

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var machine = TMSimMachine()

            var mode = ""
            var stepsQuantity = 0
            var inAlph = ""
            var inTape = ""
            var inStates = ""

            var input:String?


            println("Please, choose mode(normal, modified) or type 'help' for more info: ")
            input = readLine()
            while(input == "help") {
                modeAndHelp(true)
                modeAndHelp(false)
                input = readLine()
                if(input!="normal" && input!="modified" && input!="help") {
                    println("Unresolved command")
                }
            }
            mode = input!!

            if(mode == "modified") {
                println("Please, specify number of steps you want your machine to execute: ")
                val regex = """\d+""".toRegex()
                input = readLine()
                while (!input!!.matches(regex)) {
                    print("Please, specify number of steps you want your machine to execute: ")
                    input = readLine()
                }
                stepsQuantity = Integer.parseInt(input)
            }

            println("Please, specify name of the file where your machine' alphabet is: ")
            inAlph = readLine()!!
            println("Please, specify name of the file where your machine' tape is: ")
            inTape = readLine()!!
            println("Please, specify name of the file where your machine' states are: ")
            inStates = readLine()!!

            machine.setInAlph(inAlph)
            machine.setInTape(inTape)
            machine.setInStates(inStates)
            machine.recompile()

            machine.main(mode, 0, stepsQuantity)

            while(mode == "modified") {
                println("Do you want to proceed execution?\n(Yes/No)")
                input = readLine()
                if(input=="Yes") {
                    println("Please, choose steps' quantity: ")
                    input = readLine()
                    stepsQuantity = Integer.parseInt(input)
                    machine.main("proceed", machine.currentState.getNumber(), stepsQuantity)
                }
            }
        }

        private fun modeAndHelp(help:Boolean) {
            if(help) {
                println("normal - just a normal mode\nmodified - program will be executed in several steps")
            }
            else {
                println("Please, choose mode(normal, modified) or type 'help' for more info: ")
            }
            return
        }


    }
}