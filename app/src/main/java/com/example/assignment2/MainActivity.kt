package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 100
    private val customPermission = "com.example.assignment2.MSE412"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Request custom dangerous permission BEFORE opening SecondActivity
        if (ContextCompat.checkSelfPermission(this, customPermission)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(customPermission),
                PERMISSION_REQUEST_CODE
            )
        }

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

        // View Image Activity button
        val viewImageBtn = findViewById<Button>(R.id.btnViewImageActivity)
        viewImageBtn.setOnClickListener {
            val intent = Intent(this, ViewImageActivity::class.java)
            startActivity(intent)
        }
    }

    // 2. Optional: Only needed if you want to delay launching SecondActivity until permission is granted
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted – nothing else required unless you want to auto-launch the activity
        }
    }
}
