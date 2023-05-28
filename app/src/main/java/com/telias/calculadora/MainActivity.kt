package com.telias.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.telias.calculadora.models.BasicCalculator
class MainActivity : AppCompatActivity() {

    lateinit var display: TextView
    lateinit var displayResult: TextView
    var num1 = 0.0
    var num2 = 0.0
    var numString = ""
    var operador: Char? = null
    var newOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById<TextView>(R.id.display)
        displayResult = findViewById<TextView>(R.id.displayResult)

        useDefaultValues()
    }

    private fun useDefaultValues() {
        num1 = 0.0
        num2 = 0.0
        operador = null
        numString = ""
        newOperation = true
        display.text = ""
        displayResult.text = "0.0"
    }

    fun handleDigitsButton(view: View) {
        val button = view as Button
        if (newOperation) {
            useDefaultValues()
        }
        newOperation = false
        appendToDisplay(button.text.toString())
        numString += button.text
    }

    fun handleOperatorsButton(view: View) {
        val button = view as Button

        //Se for uma nova operação, o valor do display será utilizado como primeiro número
        if (!newOperation && operador == null) {
            if (numString.isNotEmpty()) {
                num1 = returnCurrentDisplayNumber()
                operador = button.text.toString()[0]
                appendToDisplay(button.text.toString())
                numString = ""
            }

            //Se for continuação, o resultado anterior será utilizado como primeiro número
        } else if (operador != null) {
            num2 = returnCurrentDisplayNumber()
            val result = useCalculator()
            useDefaultValues()
            num1 = result
            operador = button.text.toString()[0]
            newOperation = false
            appendToDisplay(num1.toString())
            appendToDisplay(operador.toString())
        }
    }

    fun handleEqualButton(view: View) {
        if (numString.isNotEmpty() && operador != null) {
            num2 = returnCurrentDisplayNumber()
            val result = useCalculator()

            displayResult.text = result.toString().replace(".", ",")
            newOperation = true
        }
    }

    fun handleClearButton(view: View) {
        useDefaultValues()
    }

    private fun useCalculator(): Double {
        val calculator = BasicCalculator()
        return when (operador) {
            '+' -> calculator.sum(num1, num2)
            '-' -> calculator.subtract(num1, num2)
            '*' -> calculator.multiply(num1, num2)
            '/' -> calculator.divide(num1, num2)
            else -> 0.0
        }
    }

    private fun returnCurrentDisplayNumber(): Double {
        return numString.replace(",", ".").toDouble()
    }

    private fun appendToDisplay(digit: String) {
        display.text = ("${display.text}${digit}").replace(".", ",")
    }
}