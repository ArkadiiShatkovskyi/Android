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

        txtHours.text = dataH.toString()

    }
}