package com.zam.architecturaldesign.data

class QuoteDataSource(private val quoteDao: QuoteDao) {

    val allQuotes = quoteDao.loadAllQuotes()

    suspend fun insert(quote: Quote) = quoteDao.insert(quote)

    suspend fun delete(quote: Quote) = quoteDao.delete(quote)
}