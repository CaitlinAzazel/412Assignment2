package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Display name + student ID
        val info = findViewById<TextView>(R.id.textViewInfo)
        info.text = "Caitlin Frank - Student ID: 1280896"

        // Explicit Intent button
        val explicitBtn = findViewById<Button>(R.id.btnExplicit)
        explicitBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Implicit Intent button
        val implicitBtn = findViewById<Button>(R.id.btnImplicit)
        implicitBtn.setOnClickListener {
            val intent = Intent("com.example.assignment2.SHOW_SECOND")
            startActivity(intent)
        }
    }
}