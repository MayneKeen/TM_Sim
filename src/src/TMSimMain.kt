package src

import java.lang.Exception

class TMSimMain {                                                //just a *main* class

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





    private lateinit var parser:TMUIParser

    private lateinit var out_Gen:TMOutGen

    private var tape:MutableList<String> = parser.parseTape(inTape)
    private var states:MutableList<State> = parser.parseStates(inStates)
    private var alphabet:String = parser.parseAlphabet(inAlph)



    /*mode,
     *number of the first state(is needed for "proceed" and "modified")
     *statesQuantity - number of steps you need your machine to execute
     *  in "proceed" or "modified" modes
     */
    fun main(mode:String, number: Int, statesQuantity: Int) {
        if(!(mode=="normal" || mode=="proceed" || mode=="modified")){
            print("$mode is an incorrect mode" +
                    "correct ones are: normal, modified, proceed" +
                    "please choose one of them")
            System.exit(1)
        }
        var currentTape:MutableList<String> = tape

        when(mode) {
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







    private fun normal(tape:MutableList<String>, modified: Boolean, number: Int, statesQuantity: Int) {
        try {
            var currentTape: MutableList<String> = tape

            var currentPosition = 0


            var currentState: State = states[number]

            var counter = 0

            while (currentState.getNumber() != 0) {

                if(modified && counter==statesQuantity){
                    print("$statesQuantity steps of your program have just been executed")
                    System.exit(0)
                }


                var currentSymbol: String = currentTape[currentPosition]
                var symState = alphabet.indexOf(currentSymbol)

                var currentCommand = currentState.getMotion()[symState]
                var cNextState = currentState.getNextStates()[symState]
                var newSymbol = currentState.getSymbols()[symState]


                currentTape[currentPosition] = newSymbol

                when (currentCommand) {
                    Command.Right -> currentPosition++
                    Command.Left -> currentPosition--
                }

                currentState = states[cNextState]
            }

            counter++

            if (currentState.getNumber() == 0) {
                print("Program is finished")
                System.exit(0)
            }

        }
        catch(E:Exception){
            print("Smth went wrong..")
            System.exit(1)
        }
    }



}