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

    private fun getInputCarOrNull(string: String) : Car? {
        var _car: Car? = null
        val list: List<String> = string.split(" ")
        val inputBrandCar = list[0]

        val inputColorCar : String = if (list.size > 1) {
            list[1]
        } else {
            ""
        }
        val inputNumberCar: String = if (list.size > 2) {
            list[2]
        } else {
            ""
        }
        val inputNameOwner: String = if (list.size > 3) {
            list[3]
        } else {
            ""
        }
        val inputPassportOwner: String = if (list.size > 4) {
            list[4]
        } else {
            ""
        }

        if (inputBrandCar.isEmpty()) {
            println(OUT_ERROR_INPUT_CAR_BRAND)
        }
        if (inputColorCar.isEmpty()){
            println(OUT_ERROR_INPUT_CAR_COLOR)
        }
        if (inputNumberCar.isEmpty()){
            println(OUT_ERROR_INPUT_CAR_NUMBER)
        }

        val owner = getInputOwnerOrNull("$inputNameOwner $inputPassportOwner")

        if (owner != null) {
            _car = Car(inputBrandCar, inputColorCar, inputNumberCar, owner)
        }

        return _car
    }

    private fun parkInfoByCar() {
        val inputString = readln()
        val numberCar = getInputNumberCarOrNull(inputString)
        var bool = isNumberCar(numberCar)
        if (bool) {
            printCheckInputNumberCar(bool, numberCar)
        }
        while (!bool) {
            printCheckInputNumberCar(bool, numberCar)
            val inputStringNext = readln()
            val numberCarNext = getInputNumberCarOrNull(inputStringNext)
            bool = isNumberCar(numberCarNext)
            printCheckInputNumberCar(bool, numberCarNext)
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
        var bool = isNumberPlace(numberPlace)
        if (bool) {
            printCheckInputNumberPlace(bool, numberPlace)
        }
        while (!bool) {
            printCheckInputNumberPlace(bool, numberPlace)
            val inputStringNext = readln()
            val numberPlaceNext = getInputNumberCarOrNull(inputStringNext)
            bool = isNumberPlace(numberPlaceNext)
            printCheckInputNumberPlace(bool, numberPlaceNext)
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


    /*Constant input*/
    private const val IN_START = "/start"
    private const val IN_HELP = "/help"
    private const val IN_END = "/end"
    private const val IN_RETURN = "/return"
    private const val IN_PARK = "/park"
    private const val IN_PARK_INFO_BY_CAR = "/park_info_by_car"
    private const val IN_PARK_INFO_BY_PLACE = "/park_info_by_place"
    private const val IN_PARK_STATS = "/park_stats"
    private const val IN_PARK_ALL_STATS = "/park_all_stats"

    /*Constant output*/
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

    /*Constant error*/
    private const val OUT_ERROR_INPUT_OWNER_NAME = "Invalid value - OWNER NAME. " +
            "\nThe OWNER NAME value can't be empty and must consist of at least 1 letter."
    private const val OUT_ERROR_INPUT_OWNER_PASSPORT_EMPTY = "Invalid value - OWNER PASSPORT. " +
            "\nThe PASSPORT value can't be empty."
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