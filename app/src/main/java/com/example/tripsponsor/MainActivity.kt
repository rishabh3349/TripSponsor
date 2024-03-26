package com.example.tripsponsor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import dev.shreyaspatil.easyupipayment.model.TransactionStatus

class MainActivity : AppCompatActivity(), PaymentStatusListener {

    private lateinit var easyUpiPayment: EasyUpiPayment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val amount:EditText=findViewById(R.id.amount)
        val pay:Button=findViewById(R.id.contribute)
        pay.setOnClickListener {
            pay.setOnClickListener {
                val amount:String=amount.text.toString()+".00"
                if(amount.isEmpty()){
                    Toast.makeText(this,"Enter Upi Id", Toast.LENGTH_SHORT).show()
                }
                else{
                    makePayment(amount)
                }
            }
        }
    }

    private fun makePayment(amount: String?) {

        val payeeName = "abcd"
        val transactionId = "abcd"
        val transactionRefId = "abcd"
        val payeeMerchantCode = "rishabh3349@okhdfcbank"
        val description = "courses"

        try {
            easyUpiPayment = EasyUpiPayment(this) {
                this.payeeVpa = "rishabh3349@okhdfcbank"
                this.payeeName = payeeName
                this.transactionId = transactionId
                this.transactionRefId = transactionRefId
                this.payeeMerchantCode = payeeMerchantCode
                this.description = description
                this.amount = amount.toString()
            }
            easyUpiPayment.setPaymentStatusListener(this)
            easyUpiPayment.startPayment()
        } catch (e: Exception) {
            e.printStackTrace()
            toast("Error: ${e.message}")
        }


    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Log.d("TransactionDetails", transactionDetails.toString())

        when (transactionDetails.transactionStatus) {
            TransactionStatus.SUCCESS -> onTransactionSuccess()
            TransactionStatus.FAILURE -> onTransactionFailed()
            TransactionStatus.SUBMITTED -> onTransactionSubmitted()
        }
    }

    override fun onTransactionCancelled() {
        toast("Cancelled by user")
    }

    private fun onTransactionSuccess() {
        toast("Success")
        startActivity(Intent(this,ThankYouPage::class.java))
    }
    private fun onTransactionSubmitted() {
        toast("Pending | Submitted")
    }
    private fun onTransactionFailed() {
        toast("Failed")
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}