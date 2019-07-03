package src

class State {



    private var number:Int
    private var symbols:String
    private var motion:MutableList<Command>
    private var nextStates:MutableList<Int>

    fun getNumber():Int = number

    fun getSymbols():String = symbols

    fun getMotion():MutableList<Command> = motion

    fun getNextStates():MutableList<Int> = nextStates




    private fun setNumber(number:Int) {
        this.number = number
    }

    private fun setSymbols(symbols:String) {
        this.symbols = symbols
    }

    private fun setMotion(motion:MutableList<Command>) {
        this.motion = motion
    }

    private fun setNextStates(nextStates:MutableList<Int>) {
        this.nextStates = nextStates
    }

    constructor(number:Int, symbols:String, motion:MutableList<Command>,
                nextStates:MutableList<Int>) {
        this.number = number
        this.symbols = symbols
        this.motion = motion
        this.nextStates = nextStates
    }
}