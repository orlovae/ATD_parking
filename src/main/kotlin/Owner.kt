data class Owner(
    val name: String,
    val numberPassport: Long
) {
    override fun toString() : String {
        return "$name $numberPassport"
    }
}