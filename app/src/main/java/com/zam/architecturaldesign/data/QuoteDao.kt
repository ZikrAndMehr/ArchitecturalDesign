package com.zam.architecturaldesign.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert
    suspend fun insert(quote: Quote)

    @Delete
    suspend fun delete(quote: Quote)

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun loadAllQuotes(): Flow<List<Quote>>
}