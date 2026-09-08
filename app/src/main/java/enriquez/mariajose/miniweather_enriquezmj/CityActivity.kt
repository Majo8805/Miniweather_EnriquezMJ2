package enriquez.mariajose.miniweather_enriquezmj

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import enriquez.mariajose.miniweather_enriquezmj.utilities.WeatherService

class CityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_city)
        androidx.core.view.WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinnerCity = findViewById<Spinner>(R.id.spCity)
        val btnSave = findViewById<Button>(R.id.btn_save_city)
        val weatherService = WeatherService()
        val cities = weatherService.getCities()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)
        spinnerCity.adapter = adapter

        btnSave.setOnClickListener {
            val selectedCity = spinnerCity.selectedItem.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("city", selectedCity)
            startActivity(intent)
        }
    }
}