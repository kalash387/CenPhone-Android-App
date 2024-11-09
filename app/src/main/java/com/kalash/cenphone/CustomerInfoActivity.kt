//Group Project Milestone 2
//Group No. 8
//Deepu Ajay - 301494114
//Kalash Rami - 301475553
//Date: 7th Nov, 2024

package com.kalash.cenphone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kalash.milestone_2.R

class CustomerInfoActivity : AppCompatActivity() {

    // Declare the EditText fields for customer info
    private lateinit var fullNameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var postalCodeEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var confirmInfoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)

        // Set the background color to white
        window.decorView.setBackgroundColor(resources.getColor(android.R.color.white))

        // Initialize the EditText views
        fullNameEditText = findViewById(R.id.fullNameEditText)
        addressEditText = findViewById(R.id.addressEditText)
        cityEditText = findViewById(R.id.cityEditText)
        postalCodeEditText = findViewById(R.id.postalCodeEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)

        // Initialize the Confirm Button
        confirmInfoButton = findViewById(R.id.confirmInfoButton)

        // Set the click listener for the "Confirm" button
        confirmInfoButton.setOnClickListener {
            // Validate that all fields are filled in
            if (isValidInput()) {

                val selectedBrand = intent.getStringExtra("selectedBrand") ?: "iPhone"
                val selectedModel = intent.getStringExtra("selectedModel")
                val selectedPrice = intent.getStringExtra("selectedPrice")
                val selectedStorage = intent.getStringExtra("selectedStorage")
                val selectedColor = intent.getStringExtra("selectedColor")


                // Create an Intent to navigate to PaymentActivity
                val intent = Intent(this@CustomerInfoActivity, PaymentActivity::class.java)

                // Pass customer information to PaymentActivity
                intent.putExtra("fullName", fullNameEditText.text.toString())
                intent.putExtra("address", addressEditText.text.toString())
                intent.putExtra("city", cityEditText.text.toString())
                intent.putExtra("postalCode", postalCodeEditText.text.toString())
                intent.putExtra("phone", phoneEditText.text.toString())
                intent.putExtra("email", emailEditText.text.toString())
                intent.putExtra("selectedBrand", selectedBrand)
                intent.putExtra("selectedModel", selectedModel)
                intent.putExtra("selectedPrice", selectedPrice)
                intent.putExtra("selectedStorage", selectedStorage)
                intent.putExtra("selectedColor", selectedColor)

                // Start PaymentActivity
                startActivity(intent)
            } else {
                // Show error message if the fields are not properly filled
                Toast.makeText(this@CustomerInfoActivity, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Check if all fields are filled in
    private fun isValidInput(): Boolean {
        return fullNameEditText.text.isNotEmpty() &&
                addressEditText.text.isNotEmpty() &&
                cityEditText.text.isNotEmpty() &&
                postalCodeEditText.text.isNotEmpty() &&
                phoneEditText.text.isNotEmpty() &&
                emailEditText.text.isNotEmpty()
    }
}