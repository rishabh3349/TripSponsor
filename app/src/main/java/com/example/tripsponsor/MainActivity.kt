package com.example.tripsponsor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val amount:EditText=findViewById(R.id.amount)
        val pay:Button=findViewById(R.id.contribute)
        pay.setOnClickListener {
            val intent= Intent(this,PaymentActivity::class.java)
            intent.putExtra("amount",amount.toString())
        }
    }
}