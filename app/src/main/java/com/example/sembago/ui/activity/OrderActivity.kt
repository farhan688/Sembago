package com.example.sembago.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sembago.R

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val btnOrderFix: Button = findViewById(R.id.btnDetailOrder)

        btnOrderFix.setOnClickListener {
            val intent = Intent(this, DetailOrderActivity::class.java)
            startActivity(intent)
        }
    }
}