package src

import java.io.File

/*should contain a copy of current tape
*and some methods to make an output
*/

class TMOutGen {
    constructor()

    fun write(text: String, outputName: String){
        val outputStream = File(outputName).bufferedWriter()
        outputStream.write(text)
        outputStream.close()
    }
    @Override
    fun write(text:List<String>, outputName: String) {
        val outputStream = File(outputName).bufferedWriter()
        for(line in text) {
            outputStream.write(line)
            outputStream.newLine()
        }
        outputStream.close()
    }

    fun clear(clearName:String) {
        File(clearName).delete()
    }
}