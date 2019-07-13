package src

import java.lang.Exception
import java.lang.IllegalArgumentException

class TMSimMachine() {                                               //just a *main* class

    private var inTape = "tape.txt"
    private var inStates = "states.txt"
    private var inAlph = "alphabet.txt"

    fun setInTape(input:String){
        if(input != "") {
            inTape = input
        }
        else return
    }
    fun setInStates(input:String){
        if(input != "") {
            inStates = input
        }
        else return
    }

    fun getInTape() = inTape
    fun getInStates() = inStates





    private var parser:TMUIParser = TMUIParser()

    private var outGen:TMOutGen = TMOutGen()

    private var tape:String = parser.parseTape(inTape)
    private var alphabet:String = parser.parseAlphabet(inAlph)
    private var states:MutableList<State> = parser.parseStates(inStates, alphabet)




    /*mode,
     *number of the first state(is needed for "proceed" and "modified")
     *statesQuantity - number of steps you need your machine to execute
     *  in "proceed" or "modified" modes
     */
    fun main(mode:String, number: Int, statesQuantity: Int) {
        if(!(mode=="normal" || mode=="proceed" || mode=="modified")){
            outGen.write("$mode is an incorrect mode" +
                    "correct ones are: normal, modified, proceed" +
                    "please choose one of them", "log.txt")
            System.exit(1)
        }
        val currentTape:String = tape

        try {
            when (mode) {
                //"normal" - just a normal mode
                "normal" -> normal(currentTape, false, 0, 0)

                //"modified" - program will be executed in $statesQuantity$ steps
                "modified" -> normal(currentTape, true, 0, statesQuantity)

                /*"proceed" - program' execution will be continued
             *if you wanna continue its execution in several steps, just choose
             * $statesQuantity$!=0
            */
                "proceed" -> normal(currentTape, false, number, statesQuantity)
            }
        }
        catch(E:IllegalArgumentException) {
            outGen.write(E.message.toString(), "log.txt")
            System.exit(1)
        }
    }







    private fun normal(tape:String, modified: Boolean, number: Int, statesQuantity: Int) {
            var currentTape: String = tape
            var currentPosition = 0


            var currentState: State = states[number]
            var counter = 0

            try {
                while (currentState.getNumber() != 0) {

                    if (modified && counter == statesQuantity) {
                        print("$statesQuantity steps of your program have just been executed")
                        System.exit(0)
                    }


                    val currentSymbol: Char = currentTape[currentPosition]
                    val symState = alphabet.indexOf(currentSymbol)

                    val currentCommand = currentState.getMotion()[symState]
                    val cNextState = currentState.getNextStates()[symState]
                    val newSymbol = currentState.getSymbols()[symState]


                    val temp = currentTape.toMutableList()
                    temp[currentPosition] = newSymbol
                    currentTape = temp.toString()

                    when (currentCommand) {
                        Command.Right -> currentPosition++
                        Command.Left -> currentPosition--
                        Command.IllegalCommand -> {
                            currentTape = tape
                            throw IllegalArgumentException(
                                "Illegal command has been found" +
                                        " at ${currentState.getNumber()} state, symbol:($currentSymbol) " +
                                        "maybe you haven't specified this state"
                            )
                        }

                        else -> currentPosition
                    }
                    currentState = states[cNextState]

                    counter++
                }



                if (currentState.getNumber() == -1) {
                    print("Program is finished")
                    outGen.write(currentTape, "output.txt")
                    //output
                    System.exit(0)
                }

            }
        catch (E:IllegalArgumentException) {
            outGen.write(E.message.toString(), "log.txt")
            outGen.write(currentTape, "output.txt")

            System.exit(1)
        }
        catch(E:Exception){
            outGen.write(E.message.toString(), "log.txt")
            outGen.write(currentTape, "output.txt")
            System.exit(1)
        }
    }



}