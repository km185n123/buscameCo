package com.buscame.oncor.buscame.Historical.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.Toast
import com.buscame.oncor.buscame.Historical.Activity.Object.HistoricalAdapter
import com.buscame.oncor.buscame.Historical.HistoricalMVP
import com.buscame.oncor.buscame.Historical.Model.Historic
import com.buscame.oncor.buscame.Historical.Presenter.HistoricalPresenter
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Util.DataCache
import com.buscame.oncor.buscame.Util.DatePickerFragment
import com.google.gson.JsonDeserializationContext
import kotlinx.android.synthetic.main.activity_historical.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*




class Historical : AppCompatActivity() , HistoricalMVP.View{
    var presenter =  HistoricalPresenter(this)
    var startDate = Date()
    var endDate = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical)

        dataFromTextEditText.setOnClickListener {
            showDatePickerDialog(dataFromTextEditText)



        }

        dataToTextEditText.setOnClickListener {
            showDatePickerDialog(dataToTextEditText)



        }

        var accessToken =   DataCache.readData(this,"accessToken")



        btnHistoric.setOnClickListener{

            startDate = date(dataFromTextEditText.text.toString())
            endDate = date(dataToTextEditText.text.toString())
            presenter.historical(startDate, endDate,"code-1234",accessToken)
        }




    }

    override fun loadHistoric(historicForDay: Map<String, List<Historic>>) {




        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this)
        historicalRecycler.layoutManager = layoutManager
        historicalRecycler.setHasFixedSize(true)


        val historicAdapter = HistoricalAdapter(this,historicForDay)

        historicalRecycler.adapter = historicAdapter


    }


    fun date(dateInString:String): Date{
        val formatter = SimpleDateFormat("dd-MM-yyyy")


        var date = Date()
        try {

             date = formatter.parse(dateInString)



        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date
    }


    override fun notification(message: String) {
      Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun notificationError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }







    private fun showDatePickerDialog(fielDate: EditText) {
        val newFragment = DatePickerFragment.newInstance { datePicker, year, month, day ->
            // +1 because january is zero
            val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
            fielDate.setText(selectedDate)
        }
        newFragment.show(this.fragmentManager, "datePicker")
    }


}

/*class CustomerDateAndTimeDeserialize : JsonDeserializer<Date> {

    private val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss")

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {

        val str = paramJsonParser.getText().trim()
        try {
            return dateFormat.parse(str)
        } catch (e: ParseException) {
            // Handle exception here
        }

        return paramDeserializationContext.parseDate(str)

    }




}*/
