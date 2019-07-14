package src

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class TMSimTests{

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }


    @Test
    @Tag("")
    fun testTMSimMachine() {
        var machine = TMSimMachine()
        machine.setAlphabet("01_")
        machine.setTape("_001001100")

        var state0 = State(0,"11_".toMutableList(),
            mutableListOf(Command.Right, Command.Right, Command.Right),
            mutableListOf(0, 0, 0))
        machine.setStates(mutableListOf(state0))


        machine.main("normal",0, 0)
        assertFileContent("output.txt", "11_")
    }







}

