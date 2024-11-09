//Group Project Milestone 2
//Group No. 8
//Deepu Ajay - 301494114
//Kalash Rami - 301475553
//Date: 7th Nov, 2024

package com.kalash.cenphone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kalash.milestone_2.R

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Retrieve order details from intent extras
        val fullName = intent.getStringExtra("fullName") ?: "N/A"
        val address = intent.getStringExtra("address") ?: "N/A"
        val city = intent.getStringExtra("city") ?: "N/A"
        val postalCode = intent.getStringExtra("postalCode") ?: "N/A"
        val phone = intent.getStringExtra("phone") ?: "N/A"
        val email = intent.getStringExtra("email") ?: "N/A"
        val selectedBrand = intent.getStringExtra("selectedBrand") ?: "N/A"
        val selectedModel = intent.getStringExtra("selectedModel") ?: "N/A"
        val selectedPrice = intent.getStringExtra("selectedPrice") ?: "N/A"
        val selectedStorage = intent.getStringExtra("selectedStorage") ?: "N/A"
        val selectedColor = intent.getStringExtra("selectedColor") ?: "N/A"

        // Set confirmation message
        val confirmationMessageTextView = findViewById<TextView>(R.id.confirmationMessageTextView)
        confirmationMessageTextView.text = "Your phone order was successfully completed!"

        // Display order summary details
        val orderSummaryTextView = findViewById<TextView>(R.id.orderSummaryTextView)
        orderSummaryTextView.text = """
            Customer Name: $fullName
            Address: $address, $city, $postalCode
            Phone: $phone
            Email: $email
            Brand: $selectedBrand
            Model: $selectedModel
            Price: $selectedPrice
            Storage: $selectedStorage
            Color: $selectedColor
        """.trimIndent()

        // Finish button setup to navigate back to MainActivity
        val finishButton = findViewById<Button>(R.id.finishButton)
        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() // Clears the activity stack and starts MainActivity as the root activity
        }
    }
}