package edu.udb.ejercicio2

class calcular(val nombre: String, val salarioBase: Double) {

    private val AFP = 0.0725
    private val ISSS = 0.03

    fun calcularAFP(): Double {
        return salarioBase * AFP
    }

    fun calcularISSS(): Double {
        return salarioBase * ISSS
    }
    fun calcularRenta(): Double {
        return when {
            salarioBase <= 472.0 -> 0.0
            salarioBase <= 895.24 -> (salarioBase - 472.0) * 0.10 + 17.67
            salarioBase <= 2038.10 -> (salarioBase - 895.24) * 0.20 + 60.0
            else -> (salarioBase - 2038.10) * 0.30 + 288.57
        }
    }

    fun calcularSalarioNeto(): Double {
        val afp = calcularAFP()
        val isss = calcularISSS()
        val renta = calcularRenta()
        return salarioBase - afp - isss - renta
    }
}