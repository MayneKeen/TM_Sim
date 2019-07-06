package src

class State(
    private var number: Int,
    private var symbols: MutableList<Char>,
    private var motion: MutableList<Command>,
    private var nextStates: MutableList<Int>
) {


    fun getNumber():Int = number

    fun getSymbols():MutableList<Char> = symbols

    fun getMotion():MutableList<Command> = motion

    fun getNextStates():MutableList<Int> = nextStates

    private fun setNumber(number:Int) {
        this.number = number
    }

    private fun setSymbols(symbols:MutableList<Char>) {
        this.symbols = symbols
    }

    private fun setMotion(motion:MutableList<Command>) {
        this.motion = motion
    }

    private fun setNextStates(nextStates:MutableList<Int>) {
        this.nextStates = nextStates
    }

}