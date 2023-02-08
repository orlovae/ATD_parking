import kotlin.system.exitProcess

object IO {
    val manager = ManagerImpl(Parking(20))
    fun readConsole() {
        while (true) {
            when (readlnOrNull()) {
                IN_START -> println(OUT_START)
                IN_HELP -> println(OUT_HELP)
                IN_END -> end()
                IN_RETURN -> retunCar()
                IN_PARK -> parkCar()
                else -> println(OUT_UNKNOWN)
            }
        }
    }

    private fun end() {
        println(OUT_END)
        exitProcess(0)
    }

    private fun retunCar() {
        val inputString = readln()
        var owner = getInputOwnerOrNull(inputString)
        while (owner == null) {
            val inputStringNext = readln()
            owner = getInputOwnerOrNull(inputStringNext)
        }

       val car = manager.getCarOwner(owner)

        /*По идее нужно не исключение бросать, а писать, что невозможно вернуть машину, так как машины то и нету.*/
        if (car != null) {
            println("Car $car get owner $owner")
        } else {
            throw Exception(ERROR_PARKING_CAR)
        }
    }

    private fun getInputOwnerOrNull(string: String) : Owner? {
        var _owner: Owner? = null
        val list: List<String> = string.split(" ")
        val inputName = list[0]

        val inputPassport : String = if (list.size > 1) {
            list[1]
        } else {
            ""
        }

        if (inputName.isEmpty()) {
            println(OUT_ERROR_INPUT_OWNER_NAME)
        }
        if (inputPassport.isEmpty()){
            println(OUT_ERROR_INPUT_OWNER_PASSPORT_EMPTY)
        }
        if (inputPassport.toLongOrNull() != null && inputName.isNotEmpty()) {
            _owner = Owner(inputName, inputPassport.toLongOrNull()!!)
        } else {
            println(OUT_ERROR_INPUT_OWNER_PASSPORT_NO_LONG)
        }

        return _owner
    }
    private fun parkCar() {
        val inputString = readln()
        var car = getInputOwnerOrNull(inputString)
        while (car == null) {
            val inputStringNext = readln()
            car = getInputOwnerOrNull(inputStringNext)
        }

        manager.parkingCar(car)
    }


    /*Constant input*/
    private const val IN_START = "/start"
    private const val IN_HELP = "/help"
    private const val IN_END = "/end"
    private const val IN_RETURN = "/return"
    private const val IN_PARK = "/park"

    /*Constant output*/
    private const val OUT_START = "Hello user!"
    private const val OUT_HELP = "/start - starting this program." +
            "\n/end - end and exit this program." +
            "\n/help - print help this program."
    private const val OUT_UNKNOWN = "This program cannot process the request. \nPlease call /help"
    private const val OUT_END = "See you user!"

    /*Constant error*/
    private const val OUT_ERROR_INPUT_OWNER_NAME = "Invalid value - OWNER NAME. " +
            "\nThe NAME value can't be empty and must consist of at least 1 letter."
    private const val OUT_ERROR_INPUT_OWNER_PASSPORT_EMPTY = "Invalid value - OWNER PASSPORT. " +
            "\nThe PASSPORT value can't be empty."
    private const val OUT_ERROR_INPUT_OWNER_PASSPORT_NO_LONG = "Invalid value - OWNER PASSPORT. " +
            "\nThe PASSPORT value must consist of at least 6 digits."
    private const val ERROR_PARKING_CAR = "Something went wrong. Car doesn't exist"
}