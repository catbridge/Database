package com.example.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "brand")
    val carBrand: String,
    @ColumnInfo(name = "model")
    val carModel: String
)