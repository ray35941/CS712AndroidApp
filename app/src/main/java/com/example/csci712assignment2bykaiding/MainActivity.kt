package com.example.csci712assignment2bykaiding
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExplicit = findViewById<Button>(R.id.btnExplicit)
        val btnImplicit = findViewById<Button>(R.id.btnImplicit)

        // Explicit intent: directly name the target activity class
        btnExplicit.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Implicit intent: use an action string (handled via intent-filter in Manifest)
        btnImplicit.setOnClickListener {
            val intent = Intent("com.example.yourapp.OPEN_SECOND")
            startActivity(intent)
        }
    }
}

