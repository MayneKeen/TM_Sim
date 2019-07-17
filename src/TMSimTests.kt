package src

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File
import src.*

class TMSimTests{

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }


    @Test
    @Tag("Functionality")
    fun testTMSimMachine() {
        var machine = TMSimMachine()
        machine.setAlphabet("01_")
        machine.setTape("001001100_")

        var state0 = State(0,"11_".toMutableList(),
            mutableListOf(Command.Right, Command.Right, Command.Right),
            mutableListOf(0, 0, -1))
        machine.setStates(mutableListOf(state0))

        machine.main("normal",0, 0)
        assertFileContent("output.txt", "111111111_")
    }

    @Test
    @Tag("Parsing user input")
    fun secondTestTmSimMachine() {
        var machine = TMSimMachine()


        machine.main("normal",0, 0)
        assertFileContent("output.txt", "112200120_")

    }

    @Test
    @Tag("Modified mode test")
    fun thirdTestTMSimMachine() {
        var machine = TMSimMachine()

        machine.setAlphabet(machine.parser.parseAlphabet("alphabet2.txt"))
        machine.setStates(machine.parser.parseStates("states2.txt", machine.getAlphabet()) )
        machine.setTape(machine.parser.parseTape("tape2.txt"))
        machine.setOutFile("output2.txt")

        machine.main("modified",0, 5)
        assertFileContent("output2.txt", "polyt56789")
    }


    @Test
    @Tag("One more test")
    fun fourthTestTMSimMachine() {
        var machine = TMSimMachine()

        machine.setAlphabet(machine.parser.parseAlphabet("alphabet2.txt"))
        machine.setStates(machine.parser.parseStates("states2.txt", machine.getAlphabet()) )
        machine.setTape(machine.parser.parseTape("tape2.txt"))

        machine.main("normal",0, 5)
        assertFileContent("output.txt", "polytech!!")
    }

    @Test
    @Tag("Proceed test")
    fun fifthTestTMSimMachine() {
        var machine = TMSimMachine()

        machine.setAlphabet(machine.parser.parseAlphabet("alphabet2.txt"))
        machine.setStates(machine.parser.parseStates("states2.txt", machine.getAlphabet()) )
        machine.setTape(machine.parser.parseTape("tape2.txt"))
        machine.setOutFile("output2.txt")

        machine.main("modified",0, 5)

        machine.main("proceed", machine.currentState.getNumber(), 8)
        assertFileContent("output2.txt", "polytech!!")
    }

}

