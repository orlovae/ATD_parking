import kotlin.system.exitProcess

object IO {
    fun readConsole() {
        while (true) {
            when (readlnOrNull()) {
                IN_START -> println(OUT_START)
                IN_HELP -> println(OUT_HELP)
                IN_END -> end()
                else -> println(OUT_UNKNOWN)
            }
        }
    }

    private fun end() {
        println(OUT_END)
        exitProcess(0)
    }

    /*Consatant input*/
    private const val IN_START = "/start"
    private const val IN_HELP = "/help"
    private const val IN_END = "/end"

    /*Constant output*/
    private const val OUT_START = "Hello user!"
    private const val OUT_HELP = "/start - starting this program." +
            "\n/end - end and exit this program." +
            "\n/help - print help this program."
    private const val OUT_UNKNOWN = "This program cannot process the request. \nPlease call /help"
    private const val OUT_END = "See you user!"
}