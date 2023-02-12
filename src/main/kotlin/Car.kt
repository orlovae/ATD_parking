data class Car (
    val brand: String,
    val color: String,
    val number: String,
    val owner: Owner
){
    override fun toString() : String {
        return "Car $brand " +
                "$color " +
                "$number, " +
                "owner $owner"
    }
}