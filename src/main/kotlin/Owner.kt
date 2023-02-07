data class Owner(
    val name : String,
    val numberPassport : Int,
    val cars : List<Car> = emptyList()) {
    }