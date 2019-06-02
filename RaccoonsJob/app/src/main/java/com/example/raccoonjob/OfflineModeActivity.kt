package com.example.raccoonjob

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_offline_mode.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.text.DateFormat
import java.util.*


class OfflineModeActivity : AppCompatActivity() {

    private val file:String = "HoursData"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {
            // get your data here
        }
        setContentView(R.layout.activity_offline_mode)
        val date_n = DateFormat.getDateInstance().format(Date())
        dateEditText.setText(date_n.substring(0, date_n.length - 6))
    }

    override fun onBackPressed() {
        // do something
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_offline_mode, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.showData -> {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("data", readData())
                startActivity(intent)
                /**
                val data = readData()
                if(data != "") Toast.makeText(this, data, Toast.LENGTH_LONG).show()
                else Toast.makeText(this, "Don't work yet :)", Toast.LENGTH_LONG).show()
                **/
                return true
            }
            R.id.clearData -> {
                crearData()
                Toast.makeText(this, "Hours deleted", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
        val workTime = dateEditText.text.toString() + "|" + time.toString()
        Toast.makeText(this, workTime, Toast.LENGTH_LONG).show()
        saveData(workTime)
    }

    private fun convertTime(stHour: Double?, stMinutes: Double?, endHour: Double?, endMinutes: Double?): String? {
        val converterStartMinutes = stMinutes?.div(60)
        val convertedEndMinutes = endMinutes?.div(60)

        //val startM = BigDecimal(converterStartMinutes!!).setScale(2, RoundingMode.HALF_EVEN)
        //val endM = BigDecimal(convertedEndMinutes!!).setScale(2, RoundingMode.HALF_EVEN)
         /**
          *
          *
          *
          * round to 2 numbers after point ?!
          *
          *
          * **/
        val startTime = stHour?.plus(converterStartMinutes!!)
        val endTime = endHour?.plus(convertedEndMinutes!!)

        return if(endHour == 0.0){
            startTime.toString() + "-" + endTime.toString() + "|" + 24.0?.minus(startTime!!).toString()
        }else{
            startTime.toString() + "-" + endTime.toString() + "|" + endTime?.minus(startTime!!).toString()
        }
    }

    private fun saveData(data: String?){
        var tempData:String = readData()
        tempData = tempData + " \n" + data
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(tempData.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun readData(): String {
        var fileInputStream: FileInputStream?
        fileInputStream = openFileInput(file)
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()
    }

    private fun crearData(){
        val data = ""
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
