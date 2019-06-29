package src

class State {



    private var number:Int
    private var symbols:MutableList<String>
    private var alphabet:MutableList<String>
    private var motion:MutableList<Command>
    private var nextStates:MutableList<Int>

    fun getNumber():Int = number

    fun getSymbols():MutableList<String> = symbols

    fun getMotion():MutableList<Command> = motion

    fun getNextStates():MutableList<Int> = nextStates

    fun getAlphabet():MutableList<String> = alphabet


    private fun setAlphabet(alphabet:MutableList<String>) {
        this.alphabet = alphabet
    }


    private fun setNumber(number:Int) {
        this.number = number
    }

    private fun setSymbols(symbols:MutableList<String>) {
        this.symbols = symbols
    }

    private fun setMotion(motion:MutableList<Command>) {
        this.motion = motion
    }

    private fun setNextStates(nextStates:MutableList<Int>) {
        this.nextStates = nextStates
    }

    constructor(number:Int, symbols:MutableList<String>, motion:MutableList<Command>,
                nextStates:MutableList<Int>, alphabet:MutableList<String>) {
        this.number = number
        this.symbols = symbols
        this.motion = motion
        this.nextStates = nextStates
        this.alphabet = alphabet
    }
}