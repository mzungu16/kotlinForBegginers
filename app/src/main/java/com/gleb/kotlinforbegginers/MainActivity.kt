package com.gleb.kotlinforbegginers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

const val TAG: String = "myTag"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.clickBtn)
        textView.setOnClickListener {
            Toast.makeText(this, "Click happened", Toast.LENGTH_SHORT).show()
        }

        val dataClass = DataClass(string = "Moscow")
        val textForDataClass = findViewById<TextView>(R.id.textForDataClass)
        val printString = dataClass.string + "\n" + dataClass.value.toString()
        textForDataClass.gravity = Gravity.CENTER
        textForDataClass.text = printString

        val textForDataClass2 = findViewById<TextView>(R.id.textForDataClass2)
        val printString2 =
            DataCopy.dataClassCopy.string + "\n" + DataCopy.dataClassCopy.value.toString()
        textForDataClass2.text = printString2
        textForDataClass2.gravity = Gravity.CENTER

        function()
    }

    private fun function() {
        for (i in 1..5) {
            Log.d(TAG, "$i - Hello world")
        }
        for (i in 10 downTo 5) {
            Log.d(TAG, "$i - DownTo")
        }
        val size = 15;
        for (i in 10 until size) {
            Log.d(TAG, "$i - Until")
        }
    }
}

object DataCopy {
    private val dataClass = DataClass("Samara", 4)
    val dataClassCopy = dataClass.copy()
}