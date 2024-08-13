package edu.udb.ejercicio2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var salarioEditText: EditText
    private lateinit var calcularButton: Button
    private lateinit var resultadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombreEditText = findViewById(R.id.nombreEditText)
        salarioEditText = findViewById(R.id.salarioEditText)
        calcularButton = findViewById(R.id.calcularButton)
        resultadoTextView = findViewById(R.id.resultadoTextView)

        calcularButton.setOnClickListener { calcularDescuentos() }
    }

    private fun calcularDescuentos() {
        val nombre = nombreEditText.text.toString()
        val salarioBaseText = salarioEditText.text.toString()

        if (nombre.isEmpty() || salarioBaseText.isEmpty()) {
            Toast.makeText(this, "Ingrese su nombre y salario base", Toast.LENGTH_SHORT).show()
            return
        }

        val salarioBase = salarioBaseText.toDoubleOrNull()
        if (salarioBase == null || salarioBase <= 0) {
            Toast.makeText(this, "Ingrese un salario válido", Toast.LENGTH_SHORT).show()
            return
        }

        val afp = salarioBase * 0.0725
        val isss = salarioBase * 0.03
        val renta = calcularRenta(salarioBase)

        val salarioNeto = salarioBase - afp - isss - renta

        val resultado = """
            Nombre: $nombre
            Salario Base: $${String.format("%.2f", salarioBase)}
            AFP (7.25%): $${String.format("%.2f", afp)}
            ISSS (3%): $${String.format("%.2f", isss)}
            Renta: $${String.format("%.2f", renta)}
            Salario Neto: $${String.format("%.2f", salarioNeto)}
        """.trimIndent()

        resultadoTextView.text = resultado
    }

    private fun calcularRenta(salario: Double): Double {
        return when {
            salario <= 472.0 -> 0.0
            salario <= 895.24 -> (salario - 472.0) * 0.1 + 17.67
            salario <= 2038.10 -> (salario - 895.24) * 0.2 + 60.0
            else -> (salario - 2038.10) * 0.3 + 288.57
        }
    }
}