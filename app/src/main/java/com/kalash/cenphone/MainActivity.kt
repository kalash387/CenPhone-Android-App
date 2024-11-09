//Group Project Milestone 2
//Group No. 8
//Deepu Ajay - 301494114
//Kalash Rami - 301475553
//Date: 7th Nov, 2024

package com.kalash.cenphone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kalash.milestone_2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Order button
        val orderButton: Button = findViewById(R.id.orderButton)

        // Set click listener to navigate to BrandSelectionActivity
        orderButton.setOnClickListener {
            // Navigate to BrandSelectionActivity
            val intent = Intent(this, BrandSelectionActivity::class.java)
            startActivity(intent)
        }
    }
}