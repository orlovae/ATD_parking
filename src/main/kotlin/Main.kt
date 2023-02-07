fun main(args: Array<String>) {
//    IO.readConsole()
    val parking = Parking(20)
//    parking.getParking().forEach {
//            println(it)
//    }

//    println(
//        parking.getPlace(2)
//    )

    val owner = Owner("Vasya", 5401205116)
    val car = Car("Mers", "black", "x666xx", owner)
    val managerImpl = ManagerImpl(parking)
    managerImpl.parkingCar(car)

    val stateParking = managerImpl.loadStateParking()

    stateParking.forEach {
        println(it)
    }



}