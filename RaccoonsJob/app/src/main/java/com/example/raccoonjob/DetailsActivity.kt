package com.example.raccoonjob

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dataH:String = "Don't work yet"
        val bundle = intent.extras
        if (bundle != null) {
            dataH = intent.getStringExtra("data")
        }
        setContentView(R.layout.activity_details)
        showInfo(dataH)
    }

    private fun showInfo(text: String){
        txtHours.text = editText(text)
        textViewHours.text = countHours(text).toString()
        textViewMoney.text = countMoney(text, 13).toString()
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

    private fun countMoney(text: String, rate: Int): Double{
        return countHours(text) * rate
    }
}