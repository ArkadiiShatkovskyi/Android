package com.example.raccoonjob

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import java.util.Calendar
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_offline_mode.*
import java.io.*
import java.text.DateFormat
import java.util.*


class OfflineModeActivity : AppCompatActivity() {

    private val file:String = "HoursData"
    private val fileRate:String = "Rate"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {
            // get your data here
        }
        setContentView(R.layout.activity_offline_mode)
        val dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY)
        val today = Date()
        val dateOut = dateFormatter.format(today)
        dateEditText.setText(dateOut.substring(0, dateOut.length - 5))
        txtRate.setText(readRate())

        calendarDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = convertNumber(dayOfMonth) + "." + convertNumber((month + 1))
            dateEditText.setText(msg)
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setMessage("Are you sure?")
        builder.setPositiveButton("Yes"){dialog, which ->
            System.exit(1)
        }

        builder.setNegativeButton("No"){dialog,which ->}
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_offline_mode, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.showData -> {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("data", readData())
                intent.putExtra("rate", readRate())
                startActivity(intent)
                return true
            }
            R.id.clearData -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Clear data")
                builder.setMessage("Do you want to clear data?")
                builder.setPositiveButton("Yes"){dialog, which ->
                    clearData()
                    Toast.makeText(this, "Hours deleted", Toast.LENGTH_LONG).show()
                }

                builder.setNegativeButton("No"){dialog,which ->}
                val dialog: AlertDialog = builder.create()
                dialog.show()
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

        /** Saving rate **/
        saveRate(txtRate.text.toString())
    }

    private fun convertTime(stHour: Double?, stMinutes: Double?, endHour: Double?, endMinutes: Double?): String? {
        val startTime = stHour?.plus(convertMinutes(stMinutes!!))
        val endTime = endHour?.plus(convertMinutes(endMinutes!!))

        return if(endHour == 0.0){
            startTime.toString() + "-" + endTime.toString() + "/" + 24.0?.minus(startTime!!).toString()
        }else{
            startTime.toString() + "-" + endTime.toString() + "/" + endTime?.minus(startTime!!).toString()
        }
    }

    private fun convertMinutes(minutes: Double): Double{
        val temp = minutes.div(60)
        //var result = "%.2f".format(temp).toDouble()
        //return "%.2f".format(temp).toDouble()
        return temp
    }

    private fun saveRate(rate: String?){
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(fileRate, Context.MODE_PRIVATE)
            fileOutputStream.write(rate?.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun readRate(): String?{
        var fileInputStream: FileInputStream?
        fileInputStream = openFileInput(fileRate)
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()

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

    private fun clearData(){
        val data = ""
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun convertNumber(number: Int): String{
        if(number < 10) return "0" + number.toString()
        else return number.toString()
    }
}
