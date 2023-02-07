import java.util.Collections

class Parking(numberPlaces: Int) {
    private val _places = mutableListOf<Place>()
    private var places = listOf<Place>()
    init{
        numberPlaces - Config.START_INDEX
        for (i in 0 ..numberPlaces) {
            _places.add(
                Place(Config.SYMBOL + i)
            )
        }
        places = Collections.unmodifiableList(_places)
    }

    fun getParking(): List<Place> {
        return places
    }

    fun getPlace(index: Int): Place {
        return places[index]
    }
}