package com.example.csci712assignment2bykaiding

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {

        const val ACTION_OPEN_SECOND = "com.example.csci712assignment2bykaiding.OPEN_SECOND"


        const val ACTION_MY_ACTION = "com.example.MY_ACTION"
    }

    private val receiver = MyBroadcastReceiver()
    private var receiverRegistered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnExplicit = findViewById<Button>(R.id.btnExplicit)
        val btnImplicit = findViewById<Button>(R.id.btnImplicit)


        val btnStartService = findViewById<Button>(R.id.btnStartService)
        val btnSendBroadcast = findViewById<Button>(R.id.btnSendBroadcast)


        val btnViewImageActivity = findViewById<Button>(R.id.btnViewImageActivity)


        btnExplicit.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }


        btnImplicit.setOnClickListener {
            val intent = Intent(ACTION_OPEN_SECOND)
            intent.setPackage(packageName)
            startActivity(intent)
        }


        btnStartService.setOnClickListener {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }


        btnSendBroadcast.setOnClickListener {
            val intent = Intent(ACTION_MY_ACTION).apply {
                setPackage(packageName)
            }
            sendBroadcast(intent)
        }


        btnViewImageActivity.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        if (!receiverRegistered) {
            val filter = IntentFilter(ACTION_MY_ACTION)

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




