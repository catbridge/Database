package com.example.database

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.database.room.CarDatabase

class Database : Application(){

    private var _database : CarDatabase? = null
    val database get() = requireNotNull(_database)

    override fun onCreate() {
        super.onCreate()
        _database = Room.databaseBuilder(
            this,
            CarDatabase::class.java,
            "cars.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}

val Context.carDatabase: CarDatabase
    get() = when{
        this is Database -> database
        else -> applicationContext.carDatabase
    }
