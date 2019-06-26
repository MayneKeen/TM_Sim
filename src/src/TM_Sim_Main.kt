package src

class TM_Sim_Main {                                                //just a *main* class

    private var inTape = "inTape.txt"
    private var inStates = "inStates.txt"


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
    private lateinit var out_Gen:TM_Out_Gen

    private var tape:List<String> = parser.parseTape(inTape)
    private var states:List<String> = parser.parseStates(inStates)





    fun main(inputT:String, inputS:String) {

        TODO()

    }

}