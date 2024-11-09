//Group Project Milestone 2
//Group No. 8
//Deepu Ajay - 301494114
//Kalash Rami - 301475553
//Date: 7th Nov, 2024

package com.kalash.cenphone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kalash.milestone_2.R

class ModelSelectionActivity : AppCompatActivity() {

    private var selectedModel: String? = null
    private var selectedStorage: String? = null
    private var selectedColor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_model_selection)

        // Handle window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the ImageView for the selected brand's phone image
        val selectedBrand = intent.getStringExtra("selectedBrand") ?: "iPhone"
        val phoneImageView: ImageView = findViewById(R.id.phoneImageView)
        val imageResId = getPhoneImageForBrand(selectedBrand)
        phoneImageView.setImageResource(imageResId)

        // Set up ListView for phone models with prices based on the selected brand
        val modelListView: ListView = findViewById(R.id.modelListView)
        val modelsWithPrices = getModelsWithPricesForBrand(selectedBrand)
        val modelAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, modelsWithPrices)
        modelListView.adapter = modelAdapter

        // Capture selected model
        modelListView.setOnItemClickListener { _, _, position, _ ->
            selectedModel = modelsWithPrices[position]
        }

        // Storage selection
        val storageSelectionGroup: RadioGroup = findViewById(R.id.storageSelectionGroup)

        // Set up Spinner for color selection
        val colorSelectionSpinner: Spinner = findViewById(R.id.colorSelectionSpinner)
        val colorOptions = listOf("Black", "Silver", "Gold")
        val colorAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorOptions)
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSelectionSpinner.adapter = colorAdapter

        // Capture selected color
        colorSelectionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedColor = colorOptions[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedColor = null
            }
        }

        // Handle Proceed button click
        findViewById<Button>(R.id.proceedButton).setOnClickListener {
            val selectedStorageId = storageSelectionGroup.checkedRadioButtonId

            if (selectedModel != null && selectedStorageId != -1 && selectedColor != null) {
                selectedStorage = findViewById<RadioButton>(selectedStorageId).text.toString()

                // Determine the price based on the selected model
                val selectedPrice = when (selectedModel) {
                    "Samsung Galaxy S22" -> "$799"
                    "Samsung Galaxy S22+" -> "$999"
                    "Samsung Galaxy S22 Ultra" -> "$1199"
                    "iPhone 13" -> "$799"
                    "iPhone 13 Pro" -> "$999"
                    "iPhone 13 Pro Max" -> "$1099"
                    else -> "$899"
                }

                // Pass data to CheckoutActivity
                val intent = Intent(this, CheckoutActivity::class.java).apply {
                    putExtra("selectedBrand", selectedBrand)
                    putExtra("selectedModel", selectedModel)
                    putExtra("selectedPrice", selectedPrice)
                    putExtra("selectedStorage", selectedStorage)
                    putExtra("selectedColor", selectedColor)
                }

                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a model, storage, and color", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enableEdgeToEdge() {
        // Edge-to-edge layout configurations
    }

    private fun getPhoneImageForBrand(brand: String): Int {
        return when (brand) {
            "Samsung" -> R.drawable.samsung
            "Google Pixel" -> R.drawable.pixel
            else -> R.drawable.iphone
        }
    }

    private fun getModelsWithPricesForBrand(brand: String): List<String> {
        return when (brand) {
            "Samsung" -> listOf("Samsung Galaxy S22 - $799", "Samsung Galaxy S22+ - $999", "Samsung Galaxy S22 Ultra - $1199")
            "Google Pixel" -> listOf("Pixel 6 - $599", "Pixel 6 Pro - $899", "Pixel 6a - $399")
            else -> listOf("iPhone 13 - $799", "iPhone 13 Pro - $999", "iPhone 13 Pro Max - $1099")
        }
    }
}
