import java.util.Collections

class ManagerImpl(private var parking: Parking) : Manager {
    private var _map = mutableMapOf<Place, Car?>()
    private var counterParking: Int

    init {
        parking.getParking().forEach {
            _map[it] = null
        }
        counterParking = 0
    }

    override fun parkingCar(car: Car) : Boolean {
        _map.forEach { (key, value) ->
            if (value == null) {
                _map += key to car
                counterParking++
                return true
            }
        }
        return false
    }

    override fun getCarOwner(owner: Owner) : Car? {
        val filteredMap = _map.filter { (_, value) ->
            value?.owner == owner
        }

        val tmpKey = filteredMap.entries.first().key
        _map[tmpKey] = null

        return filteredMap.entries.first().value
    }

    override fun getPlaceWhereParkingCar(numberCar: String) : Place? {
        val filteredMap = _map.filter { (_, value) ->
            value?.number == numberCar
        }
        return filteredMap.entries.first().key
    }

    override fun getInfoPlace(place: Place) : Car? {
        val filteredMap = _map.filter { (key, _) ->
            key == place
        }
        return filteredMap.entries.first().value
    }

    override fun getStateParking() : List<String> {
        val stateParking = mutableListOf<String>()
        _map.forEach { (key, value) ->
            val string = StringBuilder()
            string.append("Place = ")
                .append(key.number)
                .append(", ")
            if (value == null) {
                string.append(Config.FREE)
            } else {
                string.append(value.toString())
            }
            stateParking.add(string.toString())
        }
        return Collections.unmodifiableList(stateParking)
    }

    override fun getCounterParking() : Int {
        return counterParking
    }

    fun containsInputPlaceInParking(place: Place) : Boolean {
        return _map.containsKey(place)
    }

}