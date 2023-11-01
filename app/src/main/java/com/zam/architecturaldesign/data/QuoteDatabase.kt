package com.zam.architecturaldesign.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zam.architecturaldesign.utils.AppConstants

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {

        @Volatile
        private var instance: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java,
                    AppConstants.DATABASE_NAME
                ).build()
                instance = database
                database
            }
        }
    }
}