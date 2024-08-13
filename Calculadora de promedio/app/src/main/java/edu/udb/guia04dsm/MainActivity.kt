package edu.udb.guia04dsm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz de usuario
    private lateinit var inputName: EditText
    private lateinit var inputNote1: EditText
    private lateinit var inputNote2: EditText
    private lateinit var inputNote3: EditText
    private lateinit var inputNote4: EditText
    private lateinit var inputNote5: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    // Método que se llama cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Establece el archivo de diseño para esta actividad

        // Inicialización de los elementos de la interfaz de usuario utilizando findViewById
        inputName = findViewById(R.id.inputName)
        inputNote1 = findViewById(R.id.inputNote1)
        inputNote2 = findViewById(R.id.inputNote2)
        inputNote3 = findViewById(R.id.inputNote3)
        inputNote4 = findViewById(R.id.inputNote4)
        inputNote5 = findViewById(R.id.inputNote5)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewResult = findViewById(R.id.textViewResult)

        // Configuración del listener para el botón, que llama al método calculateAverage
        buttonCalculate.setOnClickListener { calculateAverage() }
    }

    // Método que realiza el cálculo del promedio y verifica si esta aprobado o reprobado
    private fun calculateAverage() {
        //valores de entrada como cadenas
        val name = inputName.text.toString()
        val note1Text = inputNote1.text.toString()
        val note2Text = inputNote2.text.toString()
        val note3Text = inputNote3.text.toString()
        val note4Text = inputNote4.text.toString()
        val note5Text = inputNote5.text.toString()

        // validaciones
        if (name.isEmpty() || note1Text.isEmpty() || note2Text.isEmpty() || note3Text.isEmpty() || note4Text.isEmpty() || note5Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa todos los datos solicitados", Toast.LENGTH_SHORT).show()
            return
        }

        // Conversión de las entradas de texto a números de tipo Double y validación de las notas
        val note1 = note1Text.toDoubleOrNull()
        val note2 = note2Text.toDoubleOrNull()
        val note3 = note3Text.toDoubleOrNull()
        val note4 = note4Text.toDoubleOrNull()
        val note5 = note5Text.toDoubleOrNull()

        if (note1 == null || note2 == null || note3 == null || note4 == null || note5 == null ||
            note1 !in 0.0..10.0 || note2 !in 0.0..10.0 || note3 !in 0.0..10.0 || note4 !in 0.0..10.0 || note5 !in 0.0..10.0) {
            Toast.makeText(this, "Por favor, ingresa notas válidas entre 0 y 10", Toast.LENGTH_SHORT).show()
            return
        }

        // Cálculo del promedio ponderado
        val average = (note1 * 0.15) + (note2 * 0.15) + (note3 * 0.20) + (note4 * 0.25) + (note5 * 0.25)

        // Determinación del estado de aprobación
        val status = if (average >= 6.0) "Aprobado" else "Reprobado"

        // Actualización del TextView para mostrar el resultado
        textViewResult.text = "Estudiante: $name\nPromedio Final: %.2f\nEstado: $status".format(average)
    }
}
