package com.example.testapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {

    @Query("SELECT * FROM colors")
    fun getAll(): Flow<List<Color>>  // Gunakan Flow untuk observasi data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg color: Color)  // Gunakan suspend untuk operasi async

    @Update
    suspend fun update(color: Color)

    @Delete
    suspend fun delete(color: Color)
}
