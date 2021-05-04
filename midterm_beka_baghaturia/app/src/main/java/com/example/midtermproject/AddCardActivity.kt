package com.example.midtermproject

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.util.*


class AddCardActivity : AppCompatActivity() {
    lateinit var card : TextView
    lateinit var cardEdit : EditText
    lateinit var nameEdit : EditText
    lateinit var dateEdit: EditText
    lateinit var name : TextView
    lateinit var cvvEdit : EditText
    lateinit var cardImage : ImageView
    lateinit var date : TextView


    lateinit var button : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)


        card = findViewById(R.id.card_number)
        name = findViewById(R.id.name)
        date = findViewById(R.id.exp_date)
        card = findViewById(R.id.image_view)

        button = findViewById(R.id.save)

        cardEdit = findViewById(R.id.edit_card_number)
        nameEdit = findViewById(R.id.edit_name)
        dateEdit = findViewById(R.id.edit_date)
        cvvEdit = findViewById(R.id.edit_cvv)

        cardEdit.addTextChangedListener {
            card.text = cardEdit.text

            if(cardEdit.text.isEmpty()){
                cardImage.visibility = View.INVISIBLE
                return@addTextChangedListener
            }
            else  if(cardEdit.text.toString()[0] == '5'){
                cardImage.setImageResource(R.drawable.master_card)
                cardImage.visibility = View.VISIBLE
            }
            else if(cardEdit.text.toString()[0] == '4'){
                cardImage.setImageResource(R.drawable.visa_card_icon)
                cardImage.visibility = View.VISIBLE
            }

        }

        nameEdit.addTextChangedListener {
            name.text = nameEdit.text
        }
        button.setOnClickListener {
            if(nameEdit.text.isEmpty()){
                nameEdit.error = "Enter name"
                return@setOnClickListener
            }

            if(cardEdit.text.length != 16){
                cardEdit.error = "16 digits needed"
                return@setOnClickListener
            }

            if(cvvEdit.text.length != 3){
                cvvEdit.error = "3 digits needed"
                return@setOnClickListener
            }

            cardEdit.setText("")
            nameEdit.setText("")
            dateEdit.setText("")
            cvvEdit.setText("")
            Toast.makeText(this,"Card added successfully",Toast.LENGTH_LONG).show()

        }

    }

    fun DataCalendar(view: View?) {
        val calend: DatePickerDialog
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val month: Int = calendar.get(Calendar.MONTH)
        val year: Int = calendar.get(Calendar.YEAR)
        calend = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth -> dateEdit.setText("${dayOfMonth}/${monthOfYear + 1}") },
            year,
            month,
            day
        )
        calend.show()
    }


}