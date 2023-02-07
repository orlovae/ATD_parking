interface Manager {

    fun parkingCar(car: Car) : Boolean
    fun getCarOwner(owner: Owner) : Car
    /*может быть возврат класса типа отчёт*/
    fun loadParking() : String
    /*может быть вывод мапы, где k-дата, v-отчёт*/
    fun loadStatistics(period: String) : String
}