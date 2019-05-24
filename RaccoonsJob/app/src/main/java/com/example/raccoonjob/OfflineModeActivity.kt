package com.example.raccoonjob

import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.radio_group
import kotlinx.android.synthetic.main.activity_offline_mode.*
import java.text.DateFormat
import java.util.*


class OfflineModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {
            // get your data here
        }
        setContentView(R.layout.activity_offline_mode)
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                if (radio.text == "Sign In"){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            })
        //val date_n = DateFormat.getDateTimeInstance().format(Date())
        val date_n = DateFormat.getDateInstance().format(Date())
        dateEditText.setText(date_n.substring(0, date_n.length - 6))
    }

    override fun onBackPressed() {
        // do something
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun startTimePicker(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            strTime.text = h.toString() + " : " + m.toString()
            //Toast.makeText(this, h.toString() + " : " + m +" : " , Toast.LENGTH_LONG).show()
        }),hour,minute,true)
        tpd.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun endTimePicker(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->
            endTime.text = h.toString() + " : " + m.toString()
        }), hour, minute,true)
        tpd.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addHours(view:View) {
        val startTime = strTime.text
        val endTime = endTime.text

        val startHour = startTime.toString().substringBefore(':').toDouble()
        val startMinutes = startTime.toString().substringAfter(':').toDouble()
        val endHour = endTime.toString().substringBefore(':').toDouble()
        val endMinutes = endTime.toString().substringAfter(':').toDouble()

        val time = convertTime(startHour, startMinutes, endHour, endMinutes)
        Toast.makeText(this, time, Toast.LENGTH_LONG).show()
    }

    private fun convertTime(stHour: Double?, stMinutes: Double?, endHour: Double?, endMinutes: Double?): String? {
        val converterStartMinutes = stMinutes?.div(60)
        val convertedEndMinutes = endMinutes?.div(60)

        val startTime = stHour?.plus(converterStartMinutes!!)
        val endTime = endHour?.plus(convertedEndMinutes!!)

        return if(endHour == 0.0){
            "Worked time: " + 24.0?.minus(startTime!!).toString()
        }else{
            "Worked time: " + endTime?.minus(startTime!!).toString()
        }
    }
}
