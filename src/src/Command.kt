package src

enum class Command(val symbol:String) {
    Right(">"), Left("<"), NoMove("."), IllegalCommand("~")
}