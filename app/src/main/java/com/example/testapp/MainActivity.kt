package com.example.testapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = ColorDatabase.getInstance(this)

        GlobalScope.launch {
            val colorRed = Color(hexColor = "#ff0000", name = "Red")
            val colorBlue = Color(hexColor = "#0000ff", name = "Blue")
            val colorGreen = Color(hexColor = "#00ff00", name = "Green")
            db.colorDao().insert(colorRed, colorBlue, colorGreen)

            // Cek apakah data masuk
            val colors = db.colorDao().getAll()
            Log.d("Database", "Colors in DB: $colors")
        }

        val x = lifecycleScope.async {
            Thread.sleep(1000)
        }
    }
}
