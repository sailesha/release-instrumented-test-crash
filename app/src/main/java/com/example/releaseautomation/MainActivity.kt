package com.example.releaseautomation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.time.Duration

class MainActivity : AppCompatActivity() {
    companion object {
        fun printDuration(duration: Duration) {
            android.util.Log.e("MainActivity", "duration: ${duration.isZero}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printDuration(Duration.ofHours(1))
    }
}