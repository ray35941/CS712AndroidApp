package com.example.csci712assignment2bykaiding

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var btnCaptureImage: Button
    private lateinit var imageViewCaptured: ImageView

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            if (bitmap != null) {
                imageViewCaptured.setImageBitmap(bitmap)
            } else {
                Toast.makeText(this, "No image captured", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btnCaptureImage = findViewById(R.id.btnCaptureImage)
        imageViewCaptured = findViewById(R.id.imageViewCaptured)

        btnCaptureImage.setOnClickListener {
            takePictureLauncher.launch(null)
        }
    }
}