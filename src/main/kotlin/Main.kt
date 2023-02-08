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

    val place = managerImpl.getPlaceWhereParkingCar(car)
    println(place)

    val car01 = managerImpl.getInfoPlace(Place("P1"))
    println("P1 is $car01")

    managerImpl.getCarOwner(owner)

    val stateParking1 = managerImpl.loadStateParking()

    stateParking1.forEach {
        println(it)
    }

    val place1 = managerImpl.getPlaceWhereParkingCar(car)
    println(place1)

    val car02 = managerImpl.getInfoPlace(Place("P1"))
    println("P1 is $car02")

}