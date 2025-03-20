package com.example.testapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    val colorViewModel: ColorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val db = ColorDatabase.getInstance(this)
//
//        GlobalScope.launch {
//            val colorRed = Color(hexColor = "#ff0000", name = "Red")
//            val colorBlue = Color(hexColor = "#0000ff", name = "Blue")
//            val colorGreen = Color(hexColor = "#00ff00", name = "Green")
//            db.colorDao().insert(colorRed, colorBlue, colorGreen)
//
//            // Cek apakah data masuk
//            val colors = db.colorDao().getAll()
//            Log.d("Database", "Colors in DB: $colors")
//        }

        colorViewModel.colorDatabase = ColorDatabase.getInstance(this)

//        val x = lifecycleScope.async {
//            Thread.sleep(1000)
//        }
        val observer = Observer<List<Color>> { colors ->
            for (color in colors) {
                println(color.name)
            }
        }

        colorViewModel.colors.observe(this@MainActivity, observer)
        colorViewModel.getColors()

        val colorBlue = Color("#0000FF", "Blue")
        colorViewModel.insertColor(colorBlue)

        val deferredNumber = colorViewModel.getNumber()
        lifecycleScope.launch {
            println(deferredNumber.await())
        }
    }
}
