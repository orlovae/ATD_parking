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
            if (key.isFree) {
                _map += key to car
                key.isFree = false
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
                    key.isFree = true
                    tmpKey = key
                }
            }
        }
        if (tmpKey != null) {
            _map.put(tmpKey!!, null)
        }
        return car
    }

    override fun getPlaceWhereParkingCar(car: Car): Place? {
        var place: Place? = null
        _map.forEach{(key, value) ->
            if (value != null) {
                if (value == car) {
                    place = key
                }
            }
        }
        return place
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