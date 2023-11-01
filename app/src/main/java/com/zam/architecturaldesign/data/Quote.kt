package com.zam.architecturaldesign.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @ColumnInfo(name = "quote_text") val quoteText: String,
    @ColumnInfo(name = "quote_author") val quoteAuthor: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
