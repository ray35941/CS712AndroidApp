package com.example.csci712assignment2bykaiding

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        // Use a unique action under YOUR package (matches your manifest)
        const val ACTION_OPEN_SECOND = "com.example.csci712assignment2bykaiding.OPEN_SECOND"

        // Your broadcast action
        const val ACTION_MY_ACTION = "com.example.MY_ACTION"
    }

    private val receiver = MyBroadcastReceiver()
    private var receiverRegistered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ✅ Old buttons
        val btnExplicit = findViewById<Button>(R.id.btnExplicit)
        val btnImplicit = findViewById<Button>(R.id.btnImplicit)

        // ✅ New buttons
        val btnStartService = findViewById<Button>(R.id.btnStartService)
        val btnSendBroadcast = findViewById<Button>(R.id.btnSendBroadcast)

        // --- Start Activity Explicitly ---
        btnExplicit.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // --- Start Activity Implicitly ---
        btnImplicit.setOnClickListener {
            val intent = Intent(ACTION_OPEN_SECOND)
            // (Optional but safe) make it explicit to your app package:
            intent.setPackage(packageName)
            startActivity(intent)
        }

        // --- Start Foreground Service ---
        btnStartService.setOnClickListener {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }

        // --- Send Broadcast ---
        btnSendBroadcast.setOnClickListener {
            val intent = Intent(ACTION_MY_ACTION).apply {
                setPackage(packageName) // helps avoid lint + keeps it inside your app
            }
            sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        if (!receiverRegistered) {
            val filter = IntentFilter(ACTION_MY_ACTION)

            // safest cross-version registration
            ContextCompat.registerReceiver(
                this,
                receiver,
                filter,
                ContextCompat.RECEIVER_NOT_EXPORTED
            )

            receiverRegistered = true
        }
    }

    override fun onStop() {
        super.onStop()

        if (receiverRegistered) {
            unregisterReceiver(receiver)
            receiverRegistered = false
        }
    }
}




