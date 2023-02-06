interface Manager {

    fun parkingCar(car: Car) : Boolean

    /* может быть возврат объекта (car, boolean) или добавить поле в Car - parking*/
    fun getCarOwner(owner: Owner) : Car
    /*может быть возврат класса типа отчёт*/
    fun loadParking() : String
    /*может быть вывод мапы, где k-дата, v-отчёт*/
    fun loadStatistics(period: String) : String
}