package enriquez.mariajose.miniweather_enriquezmj.utilities

import enriquez.mariajose.miniweather_enriquezmj.domain.Weather
import kotlin.random.Random

class WeatherService {

    val estados_Clima = arrayOf("Soleado", "Nublado", "Lluvioso", "Nevado", "Tormenta", "Viento")

    fun getCities(): List<String> {
        return listOf("Cd. Obregón", "Hermosillo", "Navojoa", "Nogales", "Guaymas")
    }

    fun generateWeather(cityName: String): Weather {
        val randomState = estados_Clima.random()
        val randomTemp = Random.nextInt(0, 45)

        return Weather(ciudad = cityName, temperatura = randomTemp, estado = randomState)
    }

    fun getWeather(city: String): Weather {
        return generateWeather(city)
    }


}