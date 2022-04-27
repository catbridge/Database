package com.example.database.room

import androidx.room.*

@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    fun getCars(): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(car: Car)

    @Delete
    fun delete(car: Car)


}