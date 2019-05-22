package com.example.raccoonjob

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {
            // get your data here
        }
        setContentView(R.layout.activity_main)
        radio_group.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { group, checkedId ->
                    val radio: RadioButton = findViewById(checkedId)
                    if (radio.text == "Offline mode"){
                        val intent = Intent(this, OfflineModeActivity::class.java)
                        startActivity(intent)
                        //Toast.makeText(applicationContext," Right button!!!",
                            //Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onBackPressed() {
        // do something
    }
}


