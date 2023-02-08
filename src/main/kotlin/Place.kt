data class Place(
    val number: String,
    var isFree: Boolean = true
){
    override fun toString(): String {
        return "Place - $number"
    }
}
