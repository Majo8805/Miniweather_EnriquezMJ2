package enriquez.mariajose.miniweather_enriquezmj

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import enriquez.mariajose.miniweather_enriquezmj.utilities.WeatherService
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        androidx.core.view.WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val tvCity = findViewById<TextView>(R.id.tvCity)
        val ivWeather = findViewById<ImageView>(R.id.ivWeather)
        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val tvWeather = findViewById<TextView>(R.id.tvWeather)
        val citySelected = intent.getStringExtra("city") ?: "Ciudad Obregón"
        val weatherService = WeatherService()
        val currentWeather = weatherService.getWeather(citySelected)

        val time = LocalTime.now().hour

        val greetingText = when (time) {
            in 5..11 -> getString(R.string.good_morning)
            in 12..19 -> getString(R.string.good_afternoon)
            else -> getString(R.string.good_evening)
        }

        tvGreeting.text = greetingText

        tvCity.text = citySelected
        tvTemperature.text = currentWeather.temperatura.toString() + "°C"
        tvWeather.text = currentWeather.estado

        when (currentWeather.estado) {
            "Soleado" -> ivWeather.setImageResource(R.drawable.ic_sunny)
            "Nublado" -> ivWeather.setImageResource(R.drawable.ic_cloudy)
            "Lluvioso" -> ivWeather.setImageResource(R.drawable.ic_rainy)
            "Nevado" -> ivWeather.setImageResource(R.drawable.ic_snowy)
            "Tormenta" -> ivWeather.setImageResource(R.drawable.ic_stormy)
            "Viento" -> ivWeather.setImageResource(R.drawable.ic_windy)
        }

    }
}