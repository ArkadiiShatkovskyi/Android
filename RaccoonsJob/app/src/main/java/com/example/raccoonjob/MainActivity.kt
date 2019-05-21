package com.example.raccoonjob

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group,
                                                 checkedId ->
            val btn: Button = findViewById(checkedId)
            }
        )
    }

    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: Button = findViewById(radio_group.checkedRadioButtonId)
        //Toast.makeText(applicationContext,"On click : ${radio.text}",
        //    Toast.LENGTH_SHORT).show()
    }
}


