package com.example.testapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Color::class], version = 1, exportSchema = false)
abstract class ColorDatabase : RoomDatabase() {

    abstract fun colorDao(): ColorDao

    companion object {
        @Volatile
        private var INSTANCE: ColorDatabase? = null

        fun getInstance(context: Context): ColorDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ColorDatabase::class.java,
                    "color_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
