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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kalash.milestone_2.R

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout_view)

        // Handle window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the passed data from the intent
        val selectedBrand = intent.getStringExtra("selectedBrand") ?: "iPhone"
        val selectedModel = intent.getStringExtra("selectedModel")
        val selectedPrice = intent.getStringExtra("selectedPrice")
        val selectedStorage = intent.getStringExtra("selectedStorage")
        val selectedColor = intent.getStringExtra("selectedColor")

        // Set up the UI with the passed data
        val brandTextView: TextView = findViewById(R.id.brandTextView)
        val modelTextView: TextView = findViewById(R.id.modelTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val storageTextView: TextView = findViewById(R.id.storageTextView)
        val colorTextView: TextView = findViewById(R.id.colorTextView)

        // Set the values to the text views
        brandTextView.text = "Brand: $selectedBrand"
        modelTextView.text = "Model: $selectedModel"
        priceTextView.text = "Price: $selectedPrice"
        storageTextView.text = "Storage: $selectedStorage"
        colorTextView.text = "Color: $selectedColor"

        // Find the "Confirm Purchase" button
        val confirmButton: Button = findViewById(R.id.confirmButton)

        // Set a click listener for the button to navigate to CustomerInfoActivity
        confirmButton.setOnClickListener {
            // Create an intent to navigate to the CustomerInfoActivity
            val intent = Intent(this@CheckoutActivity, CustomerInfoActivity::class.java)

            // Pass the selected phone details to CustomerInfoActivity
            intent.putExtra("selectedBrand", selectedBrand)
            intent.putExtra("selectedModel", selectedModel)
            intent.putExtra("selectedPrice", selectedPrice)
            intent.putExtra("selectedStorage", selectedStorage)
            intent.putExtra("selectedColor", selectedColor)

            // Start the CustomerInfoActivity
            startActivity(intent)
        }
    }

    private fun enableEdgeToEdge() {
        // Edge-to-edge layout configurations
    }
}