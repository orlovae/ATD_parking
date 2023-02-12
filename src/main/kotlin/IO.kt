import kotlin.system.exitProcess

object IO {
    private val manager = ManagerImpl(Parking(20))
    fun readConsole() {
        while (true) {
            when (readlnOrNull()) {
                IN_START -> println(OUT_START)
                IN_HELP -> println(OUT_HELP)
                IN_END -> end()
                IN_RETURN -> returnCar()
                IN_PARK -> parkCar()
                IN_PARK_INFO_BY_CAR -> parkInfoByCar()
                IN_PARK_INFO_BY_PLACE -> parkInfoByPlace()
                IN_PARK_STATS -> parkStats()
                IN_PARK_ALL_STATS -> parkAllStats()
                else -> println(OUT_UNKNOWN)
            }
        }
    }

    private fun end() {
        println(OUT_END)
        exitProcess(0)
    }

    private fun returnCar() {
        val inputString = readln()
        var owner = getInputOwnerOrNull(inputString)
        while (owner == null) {
            val inputStringNext = readln()
            owner = getInputOwnerOrNull(inputStringNext)
        }

       val car = manager.getCarOwner(owner)

         if (car != null) {
            println("$car get owner $owner")
        } else {
            println("This $owner does not belong to any car")
        }
    }

    private fun getInputOwnerOrNull(string: String): Owner? {
        var owner: Owner? = null
        val list: List<String> = string.split(" ")

        var inputName: String? = null
        var inputPassport: String? = null

        list.forEachIndexed{ index, value ->
            when (index) {
                RETURN_CAR_INPUT_INDEX_OWNER_NAME -> if (!errorHandling(TypeInputData.STRING, value)) {
                    inputName = value
                } else {
                        printError(OUT_ERROR_INPUT_OWNER_NAME)
                }

                RETURN_CAR_INPUT_INDEX_OWNER_PASSPORT -> if (!errorHandling(TypeInputData.LONG, value)) {
                    inputPassport = value
                } else {
                    printError(OUT_ERROR_INPUT_OWNER_PASSPORT_NO_LONG)
                }
            }
        }
        if (inputName?.isNotEmpty() == true && inputPassport?.toLongOrNull() != null) {
            owner = Owner(inputName!!, inputPassport!!.toLong())
        }
        return owner
    }

    private fun errorHandling(type: TypeInputData, value: String?): Boolean {
        return when (type) {
            TypeInputData.STRING -> value.isNullOrEmpty()
            TypeInputData.LONG -> value?.toLongOrNull() == null
        }
    }

    private fun printError(message: String) {
        println(message)
    }

    private fun parkCar() {
        val inputString = readln()
        var car = getInputCarOrNull(inputString)
        while (car == null) {
            val inputStringNext = readln()
            car = getInputCarOrNull(inputStringNext)
        }

        val isParking = manager.parkingCar(car)

         if (isParking) {
            println("$car parking")
        } else {
            println("This $car doesn't have a single owner")
        }
    }

    private fun getInputCarOrNull(string: String): Car? {
        var car: Car? = null
        val list: List<String> = string.split(" ")

        var inputBrandCar: String? = null
        var inputColorCar: String? = null
        var inputNumberCar: String? = null
        var inputNameOwner: String? = null
        var inputPassportOwner: String? = null

        list.forEachIndexed{ index, value ->
            when (index) {
                PARKING_CAR_INPUT_INDEX_CAR_BRAND -> if (!errorHandling(TypeInputData.STRING, value)) {
                    inputBrandCar = value
                } else {
                    printError(OUT_ERROR_INPUT_CAR_BRAND)
                }

                PARKING_CAR_INPUT_INDEX_CAR_COLOR -> if (!errorHandling(TypeInputData.STRING, value)) {
                    inputColorCar = value
                } else {
                    printError(OUT_ERROR_INPUT_CAR_COLOR)
                }

                PARKING_CAR_INPUT_INDEX_CAR_NUMBER -> if (!errorHandling(TypeInputData.STRING, value)) {
                    inputNumberCar = value
                } else {
                    printError(OUT_ERROR_INPUT_CAR_NUMBER)
                }

                PARKING_CAR_INPUT_INDEX_OWNER_NAME -> if (!errorHandling(TypeInputData.STRING, value)) {
                    inputNameOwner = value
                } else {
                    printError(OUT_ERROR_INPUT_OWNER_NAME)
                }

                PARKING_CAR_INPUT_INDEX_OWNER_PASSPORT -> if (!errorHandling(TypeInputData.LONG, value)) {
                    inputPassportOwner = value
                } else {
                    printError(OUT_ERROR_INPUT_OWNER_PASSPORT_NO_LONG)
                }
            }
        }

        val owner = getInputOwnerOrNull("$inputNameOwner $inputPassportOwner")

        if (inputBrandCar?.isNotEmpty() == true &&
            inputColorCar?.isNotEmpty() == true &&
            inputNumberCar?.isNotEmpty() == true &&
            owner != null) {
            car = Car(inputBrandCar!!, inputColorCar!!, inputNumberCar!!, owner)

        }
        return car
    }

    private fun parkInfoByCar() {
        val inputString = readln()
        val numberCar = getInputNumberCarOrNull(inputString)
        if (isNumberCar(numberCar)) {
            printCheckInputNumberCar(
                isNumberCar(numberCar),
                numberCar
            )
        }
        while (!isNumberCar(numberCar)) {
            printCheckInputNumberCar(
                isNumberCar(numberCar),
                numberCar
            )
            val inputStringNext = readln()
            val numberCarNext = getInputNumberCarOrNull(inputStringNext)
            printCheckInputNumberCar(
                isNumberCar(numberCarNext),
                numberCarNext
            )
        }
    }

    private fun isNumberCar(numberCar: String): Boolean {
        val place = manager.getPlaceWhereParkingCar(numberCar)
        return place != null
    }

    private fun printCheckInputNumberCar(isNumberCar: Boolean, numberCar: String) {
        if (isNumberCar) {
            println("Car $numberCar parking in ${manager.getPlaceWhereParkingCar(numberCar)}")
        } else {
            println(OUT_ERROR_INPUT_CAR_NUMBER_NOT_FOUND)
        }
    }

    private fun getInputNumberCarOrNull(inputString: String): String {
        return if (inputString.isNotEmpty()) {
            inputString
        } else {
            println(OUT_ERROR_INPUT_CAR_NUMBER_NOT_FOUND)
            ""
        }
    }

    private fun parkInfoByPlace(){
        val inputString = readln()
        val numberPlace = getInputNumberPlaceOrNull(inputString)

        if (isNumberPlace(numberPlace)) {
            printCheckInputNumberPlace(
                isNumberPlace(numberPlace),
                numberPlace
            )
        }
        while (!isNumberPlace(numberPlace)) {
            printCheckInputNumberPlace(
                isNumberPlace(numberPlace),
                numberPlace
            )
            val inputStringNext = readln()
            val numberPlaceNext = getInputNumberCarOrNull(inputStringNext)

            printCheckInputNumberPlace(
                isNumberPlace(numberPlaceNext),
                numberPlaceNext
            )
        }
    }

    private fun getInputNumberPlaceOrNull(inputString: String): String {
        return if (inputString.isNotEmpty()) {
            inputString
        } else {
            ""
        }
    }
    private fun isNumberPlace(numberPlace: String): Boolean {
        return manager.containsInputPlaceInParking(Place(numberPlace))
    }

    private fun printCheckInputNumberPlace(isNumberPlace: Boolean, numberPlace: String) {
        if (isNumberPlace) {
            val car = manager.getInfoPlace(Place(numberPlace))
            if (car != null) {
                println("Place $numberPlace parking in $car")
            } else {
                println("Place $numberPlace is empty")
            }
        } else {
            println(OUT_ERROR_INPUT_PLACE_NUMBER_NOT_FOUND)
        }
    }

    private fun parkStats() {
        val currentState = manager.getStateParking()
        currentState.forEach {
            println(it)
        }
    }

    private fun parkAllStats(){
        val counterParking = manager.getCounterParking()
        println("During the entire operation of the parking lot, $counterParking cars were parked")
    }


    /*Constants input data fun readConsole */
    private const val IN_START = "/start"
    private const val IN_HELP = "/help"
    private const val IN_END = "/end"
    private const val IN_RETURN = "/return"
    private const val IN_PARK = "/park"
    private const val IN_PARK_INFO_BY_CAR = "/park_info_by_car"
    private const val IN_PARK_INFO_BY_PLACE = "/park_info_by_place"
    private const val IN_PARK_STATS = "/park_stats"
    private const val IN_PARK_ALL_STATS = "/park_all_stats"

    /*Constants input data in fun parkingCar*/
    private const val PARKING_CAR_INPUT_INDEX_CAR_BRAND = 0
    private const val PARKING_CAR_INPUT_INDEX_CAR_COLOR = 1
    private const val PARKING_CAR_INPUT_INDEX_CAR_NUMBER = 2
    private const val PARKING_CAR_INPUT_INDEX_OWNER_NAME = 3
    private const val PARKING_CAR_INPUT_INDEX_OWNER_PASSPORT = 4

    /*Constants input data in fun returnCar*/

    private const val RETURN_CAR_INPUT_INDEX_OWNER_NAME = 0
    private const val RETURN_CAR_INPUT_INDEX_OWNER_PASSPORT = 1

    /*Constants output*/
    private const val OUT_START = "Hello user!"
    private const val OUT_HELP = "/start - starting this program." +
            "\n/end - end and exit this program." +
            "\n/help - print help this program." +
            "\n/return - returns the car from the parking place to the owner. " +
            "\n          Enter the data in the following format \"ivan 5401205116\"" +
            "\n/park - parks the car in an empty space. " +
            "\n        Enter the data in the following format \"mercedes black x666xx ivan 5401205116\"" +
            "\n/park_info_by_car - returns the number of the parking place where it is parked by the number of the car. " +
            "\n                    Enter the data in the following format \"x666xx" +
            "\n/park_info_by_place - by the number of the place, returns the number of the car located there, or the freedom of this place. " +
            "\n                      Enter the data in the following format \"P1" +
            "\n/park_stats - returns the current parking load." +
            "\n/park_all_stats - returns how many cars were parked during the entire operation."
    private const val OUT_UNKNOWN = "This program cannot process the request. \nPlease call /help"
    private const val OUT_END = "See you user!"

    /*Constants error*/
    private const val OUT_ERROR_INPUT_OWNER_NAME = "Invalid value - OWNER NAME. " +
            "\nThe OWNER NAME value can't be empty and must consist of at least 1 letter."
    private const val OUT_ERROR_INPUT_OWNER_PASSPORT_NO_LONG = "Invalid value - OWNER PASSPORT. " +
            "\nThe PASSPORT value must consist of at least 6 digits."
    private const val ERROR_RETURN_CAR = "Something went wrong. Car doesn't exist"
    private const val ERROR_PARKING_CAR = "Something went wrong. Car doesn't exist"
    private const val OUT_ERROR_INPUT_CAR_BRAND = "Invalid value - CAR BRAND. " +
            "\nThe CAR BRAND value can't be empty and must consist of at least 1 letter."
    private const val OUT_ERROR_INPUT_CAR_COLOR = "Invalid value - CAR COLOR. " +
            "\nThe CAR COLOR value can't be empty and must consist of at least 1 letter."
    private const val OUT_ERROR_INPUT_CAR_NUMBER = "Invalid value - CAR NUMBER. " +
            "\nThe CAR NUMBER value can't be empty and must consist of at least 1 letter."
    private const val OUT_ERROR_INPUT_CAR_NUMBER_NOT_FOUND = "Invalid value - CAR NUMBER. " +
            "\nCar NOT FOUND."
    private const val OUT_ERROR_INPUT_PLACE_NUMBER_NOT_FOUND = "Invalid value - PLACE NUMBER. " +
            "\nPlace NOT FOUND."
}