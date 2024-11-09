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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kalash.milestone_2.R

class PaymentActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var paymentMethodRadioGroup: RadioGroup
    private lateinit var creditCardRadioButton: RadioButton
    private lateinit var debitCardRadioButton: RadioButton
    private lateinit var applePayRadioButton: RadioButton
    private lateinit var googlePayRadioButton: RadioButton

    private lateinit var cardNumberEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var confirmPaymentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        // Set background color to white
        window.decorView.setBackgroundColor(resources.getColor(android.R.color.white))

        // Initialize UI elements
        paymentMethodRadioGroup = findViewById(R.id.paymentMethodRadioGroup)
        creditCardRadioButton = findViewById(R.id.creditCardRadioButton)
        debitCardRadioButton = findViewById(R.id.debitCardRadioButton)
        applePayRadioButton = findViewById(R.id.applePayRadioButton)
        googlePayRadioButton = findViewById(R.id.googlePayRadioButton)

        cardNumberEditText = findViewById(R.id.cardNumberEditText)
        expiryDateEditText = findViewById(R.id.expiryDateEditText)

        confirmPaymentButton = findViewById(R.id.confirmPaymentButton)

        // Set visibility for credit/debit card input fields based on selected payment method
        paymentMethodRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.creditCardRadioButton, R.id.debitCardRadioButton -> {
                    // Show card number and expiry date fields
                    cardNumberEditText.visibility = android.view.View.VISIBLE
                    expiryDateEditText.visibility = android.view.View.VISIBLE
                }
                else -> {
                    // Hide card number and expiry date fields for Apple Pay and Google Pay
                    cardNumberEditText.visibility = android.view.View.GONE
                    expiryDateEditText.visibility = android.view.View.GONE
                }
            }
        }

        // Set click listener for confirm payment button
        confirmPaymentButton.setOnClickListener {
            // Validate input fields
            if (isValidPaymentInput()) {
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

                // Navigate to ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)

                // Pass all data to ConfirmationActivity
                intent.putExtra("fullName", fullName)
                intent.putExtra("address", address)
                intent.putExtra("city", city)
                intent.putExtra("postalCode", postalCode)
                intent.putExtra("phone", phone)
                intent.putExtra("email", email)
                intent.putExtra("selectedBrand", selectedBrand)
                intent.putExtra("selectedModel", selectedModel)
                intent.putExtra("selectedPrice", selectedPrice)
                intent.putExtra("selectedStorage", selectedStorage)
                intent.putExtra("selectedColor", selectedColor)

                startActivity(intent)
                finish() // Optional: finish the current activity if you don't want to go back to it
            } else {
                // Show error message if fields are not properly filled
                Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Check if the user has provided valid payment input
    private fun isValidPaymentInput(): Boolean {
        // Validate credit/debit card payment method
        return when {
            creditCardRadioButton.isChecked || debitCardRadioButton.isChecked -> {
                // Check if the card number and expiry date are entered
                cardNumberEditText.text.isNotEmpty() && expiryDateEditText.text.isNotEmpty()
            }
            applePayRadioButton.isChecked || googlePayRadioButton.isChecked -> {
                // No extra input required for Apple Pay/Google Pay
                true
            }
            else -> false
        }
    }
}