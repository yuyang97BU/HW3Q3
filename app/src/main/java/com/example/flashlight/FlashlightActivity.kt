package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

class FlashlightActivity : AppCompatActivity() {

    private lateinit var flashlightSwitch: Switch
    private lateinit var userInputEditText: EditText
    private lateinit var submitButton: Button
    var isFlashlightOn: Boolean = false
    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashlightSwitch = findViewById(R.id.flashlightSwitch)
        userInputEditText = findViewById(R.id.textInput)
        submitButton = findViewById(R.id.button)

        flashlightSwitch.setOnCheckedChangeListener { _, isChecked ->
            toggleFlashlight(isChecked)
        }

        submitButton.setOnClickListener {
            val userInput = userInputEditText.text.toString().uppercase()
            when (userInput) {
                "ON" -> {
                    flashlightSwitch.isChecked = true
                }
                "OFF" -> {
                    flashlightSwitch.isChecked = false
                }
                else -> {
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
                }
            }
        }

        gestureDetector = GestureDetectorCompat(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                if (e1 != null && e2 != null) {
                    if (e2.y - e1.y > 2 && abs(velocityY) > 0.1) {
                        flashlightSwitch.isChecked = false
                        return true
                    } else if (e1.y - e2.y > 2 && abs(velocityY) > 0.1) {
                        flashlightSwitch.isChecked = true
                        return true
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    private fun toggleFlashlight(on: Boolean) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        try {
            cameraManager.setTorchMode(cameraId, on)
            isFlashlightOn = on
        } catch (e: CameraAccessException) {
            Toast.makeText(this, "Flashlight not available", Toast.LENGTH_SHORT).show()
        }
    }
}