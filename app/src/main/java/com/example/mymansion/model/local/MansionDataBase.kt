package com.example.mymansion.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymansion.model.MansionDetails
import com.example.mymansion.model.MansionItem

@Database(entities = [MansionDetails::class, MansionItem::class], version = 1)
abstract class MansionDataBase: RoomDatabase() {

    abstract fun mansionDao(): MansionDao

    companion object {

        @Volatile
        private var INSTANCE: MansionDataBase? = null

        fun getDatabase(context: Context): MansionDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MansionDataBase::class.java,
                    "mansion_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}