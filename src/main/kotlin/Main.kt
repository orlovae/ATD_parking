fun main(args: Array<String>) {
//    IO.readConsole()
    val parking = Parking(20)
    parking.getParking().forEach {
            println(it)
    }

    println(
        parking.getPlace(2)
    )
}