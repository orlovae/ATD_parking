data class Car (
    val brand : String = "",
    val color : String = "",
    val number : String = "",
    val owner : Owner
){
    var parkingPlace: Place? = null
}