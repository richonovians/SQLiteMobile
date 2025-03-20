package com.example.testapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class Color(
    @ColumnInfo(name = "hex_color") val hexColor: String,
    val name: String,
    @PrimaryKey(autoGenerate = true) val _id: Int = 0
)
