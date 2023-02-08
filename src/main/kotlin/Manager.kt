interface Manager {

    fun parkingCar(car: Car) : Boolean
    fun getCarOwner(owner: Owner) : Car?
    fun getPlaceWhereParkingCar(car: Car) : Place?
    fun loadStateParking() : List<String>
    /*может быть вывод мапы, где k-дата, v-отчёт*/
    fun loadStatistics(period: String) : String
}