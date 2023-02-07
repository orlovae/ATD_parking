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
//        parking.getParking().forEach {
//            if (it.car != null) {
//                car = it.car!!
//                val ownerCar = car!!.owner
//                if (ownerCar == owner) {
//                    car!!.parkingPlace = null
//                    it.car = null
//                }
//            }
//        }
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