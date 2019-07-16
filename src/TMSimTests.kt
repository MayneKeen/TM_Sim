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

   


}

