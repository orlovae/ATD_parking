import java.util.Collections

class ManagerImpl(private var parking: Parking) : Manager {
    private var _map = mutableMapOf<Place, Car?>()

    init {
        parking.getParking().forEach {
            _map[it] = null
        }
    }

    override fun parkingCar(car: Car): Boolean {
        _map.forEach { (key, value) ->
            if (value == null) {
                _map += key to car
                return true
            }
        }
        return false
    }

    override fun getCarOwner(owner: Owner): Car? {
        var car: Car? = null
        var tmpKey: Place? = null
        _map.forEach{(key, value) ->
            if (value != null) {
                if (value.owner == owner) {
                    car = value
                    tmpKey = key
                }
            }
        }
        if (tmpKey != null) {
            _map[tmpKey!!] = null
        }
        return car
    }

    override fun getPlaceWhereParkingCar(numberCar: String): Place? {
        var place: Place? = null
        _map.forEach{(key, value) ->
            if (value != null) {
                if (value.number == numberCar) {
                    place = key
                }
            }
        }
        return place
    }

    override fun getInfoPlace(place: Place): Car? {
        var car: Car? = null
        _map.forEach{(key, value) ->
            if (key == place) {
                if (value != null) {
                    car = value
                }
            }
        }
        return car
    }

    override fun loadStateParking(): List<String> {
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

    override fun loadStatistics(period: String): String {
        TODO("Not yet implemented")
    }

}