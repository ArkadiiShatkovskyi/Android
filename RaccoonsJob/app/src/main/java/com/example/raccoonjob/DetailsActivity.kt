package com.example.raccoonjob

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dataH:String = "Don't work yet"
        var rate: Double = 0.0
        val bundle = intent.extras
        if (bundle != null) {
            dataH = intent.getStringExtra("data")
            rate = intent.getStringExtra("rate").toDouble()
        }
        setContentView(R.layout.activity_details)

        txtHours.movementMethod = ScrollingMovementMethod()
        if(dataH == "") dataH = "Don't work yet"
        showInfo(dataH, rate)
    }

    private fun showInfo(text: String, rate: Double){

        var tempText = text
        if(tempText == "Don't work yet"){
            tempText = ""
            txtHours.text = "Don't work yet"
        } else txtHours.text = editText(tempText)

        textViewHours.text = countHours(tempText).toString()
        textViewMoney.text = countMoney(tempText, rate).toString()
    }

    private fun editText(text: String?): String{
        var result = ""
        for(str: String in getDataList(text!!)){
            result += str + "\n"
        }
        return result
    }

    private fun getDataList(text: String): List<String>{
        return text?.split(" ")
    }

    private fun countHours(text: String): Double{
        var result = 0.0
        for(l: String in getDataList(text)){
            val hour = l.substringAfter("/")
            if(hour != ""){
                result = result.plus(hour.toDouble())
            }
        }
        return result
    }

    private fun countMoney(text: String, rate: Double): Double{
        return countHours(text) * rate
    }
}