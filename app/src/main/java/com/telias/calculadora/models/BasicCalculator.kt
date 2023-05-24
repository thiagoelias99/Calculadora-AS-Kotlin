package com.telias.calculadora.models

import com.telias.calculadora.interfaces.IArithmeticDevice

class BasicCalculator : IArithmeticDevice {
    override fun sum(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    override fun subtract(num1: Double, num2: Double): Double {
        return num1 - num2
    }

    override fun multiply(num1: Double, num2: Double): Double {
        return num1 * num2
    }

    override fun divide(num1: Double, num2: Double): Double {
        return num1 / num2
    }
}