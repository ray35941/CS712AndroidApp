package com.example.csci712assignment2bykaiding

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val listView = findViewById<ListView>(R.id.listChallenges)
        val btnMain = findViewById<Button>(R.id.btnMain)

        val challenges = listOf(
            "Device fragmentation (different screen sizes/OS versions)",
            "Battery and performance optimization",
            "Network variability and offline support",
            "Security and privacy (data storage, permissions)",
            "Testing across devices and automation"
        )

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            challenges
        )

        // Return to main: simplest is finish() to go back
        btnMain.setOnClickListener {
            finish()
        }
    }
}
