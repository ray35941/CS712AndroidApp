package com.example.csci712assignment2bykaiding

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
    private val mse712Permission = "com.example.csci712assignment2bykaiding.MSE712"
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(
                    this,
                    "Permission granted.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Permission not granted.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    private val receiver = MyBroadcastReceiver()
    private var receiverRegistered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestMse712Permission()

        val btnExplicit = findViewById<Button>(R.id.btnExplicit)
        val btnImplicit = findViewById<Button>(R.id.btnImplicit)


        val btnStartService = findViewById<Button>(R.id.btnStartService)
        val btnSendBroadcast = findViewById<Button>(R.id.btnSendBroadcast)


        val btnViewImageActivity = findViewById<Button>(R.id.btnViewImageActivity)


        btnExplicit.setOnClickListener {

            checkPermissionAndOpenSecondExplicit()
        }


        btnImplicit.setOnClickListener {
            checkPermissionAndOpenSecondImplicit()
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
    private fun requestMse712Permission() {
        if (ContextCompat.checkSelfPermission(this, mse712Permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(mse712Permission)
        }
    }

    private fun checkPermissionAndOpenSecondExplicit() {
        if (ContextCompat.checkSelfPermission(this, mse712Permission)
            == PackageManager.PERMISSION_GRANTED
        ) {
            openSecondActivityExplicit()
        } else {
            permissionLauncher.launch(mse712Permission)
        }
    }

    private fun checkPermissionAndOpenSecondImplicit() {
        if (ContextCompat.checkSelfPermission(this, mse712Permission)
            == PackageManager.PERMISSION_GRANTED
        ) {
            openSecondActivityImplicit()
        } else {
            permissionLauncher.launch(mse712Permission)
        }
    }

    private fun openSecondActivityExplicit() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun openSecondActivityImplicit() {
        val intent = Intent(ACTION_OPEN_SECOND)
        intent.setPackage(packageName)
        startActivity(intent)
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




