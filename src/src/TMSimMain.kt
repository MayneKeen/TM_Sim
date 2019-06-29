package src

class TMSimMain {                                                //just a *main* class

    private var inTape = "inTape.txt"
    private var inStates = "inStates.txt"
    private var inAlph = "inAlph.txt"

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





    private lateinit var parser:TM_UI_Parser

    //private lateinit var out_Gen:TM_Out_Gen

    private var tape:MutableList<String> = parser.parseTape(inTape)
    private var states:MutableList<State> = parser.parseStates(inStates)
    private var alphabet:MutableList<String> = parser.parseAlphabet(inAlph)




    fun main(mode:String, number: Int) {

        var currentTape:MutableList<String> = tape

        when(mode) {
            "normal" -> normal(currentTape, false, 0)
            //"modified" -> modified()
            "modified" -> normal(currentTape, true, number)

        }


    }




    private fun normal(tape:MutableList<String>, modified: Boolean, number: Int) {
        //val initialTape:List<String> = tape
        var currentTape:MutableList<String> = tape

        var currentPosition = 0


        var currentState:State = states.first()

        if(modified) {
            currentState = states[number]
        }

        while(currentState.getNumber()!=0) {
            var currentSymbol:String = currentTape[currentPosition]
            var symState = currentState.getAlphabet().indexOf(currentSymbol)

            var currentCommand = currentState.getMotion()[symState]
            var cNextState = currentState.getNextStates()[symState]
            var newSymbol = currentState.getSymbols()[symState]


            currentTape[currentPosition] = newSymbol

            when(currentCommand) {
                Command.Right -> currentPosition++
                Command.Left -> currentPosition--
            }

            currentState = states[cNextState]
        }

    }


    /*private fun proceed(tape:List<String>) {

    }
    */
}