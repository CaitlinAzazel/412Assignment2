package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val challenges = listOf(
            "1. Battery consumption optimization",
            "2. Handling multiple screen sizes",
            "3. Ensuring app security",
            "4. Managing background tasks",
            "5. Network connectivity issues"
        )

        val textView = findViewById<TextView>(R.id.textViewChallenges)
        textView.text = challenges.joinToString("\n")

        // Button to return to MainActivity
        val mainBtn = findViewById<Button>(R.id.btnMain)
        mainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}