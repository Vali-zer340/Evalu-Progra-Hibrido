package com.example.evaluacion1

import Calculos_precios.CuentaMesa
import Calculos_precios.ItemMesa
import Calculos_precios.ItemMenu
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Instancias de ItemMenu y CuentaMesa como propiedades de la clase
    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 12000)
    private val cazuela = ItemMenu("Cazuela", 10000)
    private val cuentaMesa = CuentaMesa(1)

    // Declaración de vistas como propiedades de la clase
    private lateinit var cantidadPastelEditText: EditText
    private lateinit var cantidadCazuelaEditText: EditText
    private lateinit var subtotalPastelTextView: TextView
    private lateinit var subtotalCazuelaTextView: TextView
    private lateinit var switchPropina: Switch
    private lateinit var totalComidaTextView: TextView
    private lateinit var totalPropinaTextView: TextView
    private lateinit var totalFinalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar compatibilidad con bordes
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar las vistas
        cantidadPastelEditText = findViewById(R.id.pastelcantidad)
        cantidadCazuelaEditText = findViewById(R.id.cazuelacantidad)
        subtotalPastelTextView = findViewById(R.id.totalChoclo)
        subtotalCazuelaTextView = findViewById(R.id.totalCazuela)
        switchPropina = findViewById(R.id.switchpropina)
        totalComidaTextView = findViewById(R.id.totalcomida)
        totalPropinaTextView = findViewById(R.id.totalpropina)
        totalFinalTextView = findViewById(R.id.totalFinal)

        // Configurar los listeners
        configurarListeners()
    }

    private fun configurarListeners() {
        // Listener para el EditText de cantidad de Pastel de Choclo
        cantidadPastelEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cuentaMesa.agregarItem(pastelDeChoclo, cantidad) // Ajuste aquí

                // Actualizar la interfaz
                subtotalPastelTextView.text = "$" + (pastelDeChoclo.precio * cantidad)
                actualizarTotales()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        cantidadCazuelaEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cuentaMesa.agregarItem(cazuela, cantidad) // Ajuste aquí

                // Actualizar la interfaz
                subtotalCazuelaTextView.text = "$" + (cazuela.precio * cantidad)
                actualizarTotales()
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        // Listener para el Switch de propina
        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            actualizarTotales()
        }
    }

    // Método para actualizar los totales
    private fun actualizarTotales() {
        totalComidaTextView.text = "$" + cuentaMesa.calculototalSinpropina()
        totalPropinaTextView.text = "$" + cuentaMesa.calculoPropina()
        totalFinalTextView.text = "$" + cuentaMesa.CalculototaljuntoPropina()
    }
}
