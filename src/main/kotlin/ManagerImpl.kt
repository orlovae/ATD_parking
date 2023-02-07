class ManagerImpl(private var parking: Parking) : Manager {

    override fun parkingCar(car: Car): Boolean {
        if (parking.containsFreePlace()) {
            parking.getParking().forEach {
                if (it.car == null) {
                    it.car = car
                    car.parkingPlace = it
                    return true
                }
            }
        }
        return false
    }

    override fun getCarOwner(owner: Owner): Car {
        TODO("Not yet implemented")
    }

    override fun loadStateParking(): String {
        TODO("Not yet implemented")
    }

    override fun loadStatistics(period: String): String {
        TODO("Not yet implemented")
    }

}