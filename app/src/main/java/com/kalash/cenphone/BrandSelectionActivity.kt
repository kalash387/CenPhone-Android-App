//Kalash Rami - 301475553
//Date: 7th Nov, 2024

package com.kalash.cenphone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kalash.milestone_2.R

class BrandSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_selection)

        // Set onClickListeners for each brand option
        findViewById<View>(R.id.iphoneOption).setOnClickListener { onBrandSelected(it) }
        findViewById<View>(R.id.samsungOption).setOnClickListener { onBrandSelected(it) }
        findViewById<View>(R.id.googleOption).setOnClickListener { onBrandSelected(it) }
    }

    // Function to handle brand selection
    fun onBrandSelected(view: View) {
        val selectedBrand = when (view.id) {
            R.id.iphoneOption -> "iPhone"
            R.id.samsungOption -> "Samsung"
            R.id.googleOption -> "Google Pixel"
            else -> ""
        }

        // Pass selected brand to ModelSelectionActivity
        val intent = Intent(this, ModelSelectionActivity::class.java)
        intent.putExtra("selectedBrand", selectedBrand)
        startActivity(intent)
    }
}