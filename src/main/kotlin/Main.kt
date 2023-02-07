fun main(args: Array<String>) {
//    IO.readConsole()
    val parking = Parking(20)
    parking.getParking().forEach {
            println(it)
    }

    println(
        parking.getPlace(2)
    )

    val car = Car("Mers", "black", "x666xx")
    val managerImpl = ManagerImpl(parking)
    managerImpl.parkingCar(car)

    println(car.parkingPlace?.car)
    println(car.parkingPlace?.number)
}