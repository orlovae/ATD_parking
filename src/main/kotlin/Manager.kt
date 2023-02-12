interface Manager {

    fun parkingCar(car: Car): Boolean
    fun getCarOwner(owner: Owner): Car?
    fun getPlaceWhereParkingCar(numberCar: String): Place?
    fun getInfoPlace(place: Place): Car?
    fun getStateParking(): List<String>
    fun getCounterParking(): Int
}