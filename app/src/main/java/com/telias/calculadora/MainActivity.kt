package com.telias.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Error
import java.lang.Exception

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

//        val button0 = findViewById<Button>(R.id.button0)
//        val button1 = findViewById<Button>(R.id.button1)
//        val button2 = findViewById<Button>(R.id.button2)
//        val button3 = findViewById<Button>(R.id.button3)
//        val button4 = findViewById<Button>(R.id.button4)
//        val button5 = findViewById<Button>(R.id.button5)
//        val button6 = findViewById<Button>(R.id.button6)
//        val button7 = findViewById<Button>(R.id.button7)
//        val button8 = findViewById<Button>(R.id.button8)
//        val button9 = findViewById<Button>(R.id.button9)
//        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
//        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
//        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
//        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
//        val buttonEqual = findViewById<Button>(R.id.buttonEqual)
//        val buttonClear = findViewById<Button>(R.id.buttonClear)
        display = findViewById<TextView>(R.id.display)
        displayResult = findViewById<TextView>(R.id.displayResult)
        display.text = ""
        displayResult.text = "0.0"
    }

    fun addDisplay(view: View) {
        if (newOperation){
            num1 = 0.0
            num2 = 0.0
            numString = ""
            newOperation = false
            display.text = ""
            displayResult.text = "0.0"
        }
        val button = view as Button
        display.text = """${display.text}${button.text}"""
        numString += button.text
    }

    fun operador(view: View){
        val button = view as Button
//        val displayVal = display.text.toString()
        if (numString.isNotEmpty()){
            num1 = numString.toDouble()
            operador = button.text.toString()[0]
            display.text = """${display.text}${button.text}"""
        } else{
            num1 = 0.0
            operador = null
        }
        numString = ""
        Log.i("TElias", "num1: $num1")
        Log.i("TElias", "operador: $operador")
    }
    fun calculate(view: View){
//        val displayVal = display.text.toString()
        if (numString.isNotEmpty()){
            num2 = numString.toDouble()
            Log.i("TElias", "num2: $num2")
            val result = when (operador) {
                '+' -> num1 + num2
                '-' -> num1 - num2
                '*' -> num1 * num2
                '/' -> num1 / num2
                else -> 0f
            }
            displayResult.text = result.toString()
            newOperation = true
        }
    }

    fun clearDisplay(view: View) {
        display.text = ""
        displayResult.text = "0.0"
        numString = ""
    }

}
