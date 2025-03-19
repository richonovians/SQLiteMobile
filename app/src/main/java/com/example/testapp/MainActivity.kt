package com.example.testapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = ColorDatabase.getInstance(this)

        // Jalankan di coroutine agar tidak crash karena Room butuh thread terpisah
        lifecycleScope.launch {
            val colorRed = Color(0, "#ff0000", "Red")
            db.colorDao().insert(colorRed)
        }
    }
}
