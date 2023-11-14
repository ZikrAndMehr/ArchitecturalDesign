package com.zam.architecturaldesign.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.data.QuoteDataSource
import com.zam.architecturaldesign.utils.AppConstants
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class SharedViewModel(private val quoteDataSource: QuoteDataSource) : ViewModel() {

    val allQuotes = quoteDataSource.allQuotes.shareIn(
        viewModelScope, SharingStarted.Lazily, AppConstants.QUOTE_NEW_VALUE_REPLAY
    )

    fun deleteQuote(quote: Quote) {
        viewModelScope.launch {
            quoteDataSource.delete(quote)
        }
    }

    fun insertQuote(quote: Quote) {
        viewModelScope.launch {
            quoteDataSource.insert(quote)
        }
    }

    companion object {
        fun getFactory(quoteDataSource: QuoteDataSource): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SharedViewModel(quoteDataSource) as T
                }
            }
    }
}
