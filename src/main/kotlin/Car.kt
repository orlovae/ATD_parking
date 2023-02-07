data class Car (
    val brand : String = "",
    val color : String = "",
    val number : String = "",
){
    var owner : Owner? = null
    var parkingPlace: Place? = null
}