package com.example.tripsponsor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThankYouPage:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)
        val back:Button=findViewById(R.id.back)
        back.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}