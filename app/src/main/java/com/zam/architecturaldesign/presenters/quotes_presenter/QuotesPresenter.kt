package com.zam.architecturaldesign.presenters.quotes_presenter

import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.data.QuoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class QuotesPresenter(
    private val viewInterface: QuotesContract.ViewInterface,
    private val quoteDataSource: QuoteDataSource
) : QuotesContract.PresenterInterface {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)
    private lateinit var allQuotesJob: Job

    override fun startCollectingFlows() {
        allQuotesJob = coroutineScope.launch {
            quoteDataSource.allQuotes.collect {
                viewInterface.updateQuotesAdapter(it)
            }
        }
    }

    override fun stopCollectingFlows() {
        allQuotesJob.cancel()
    }

    override fun deleteQuote(quote: Quote) {
        coroutineScope.launch {
            quoteDataSource.delete(quote)
        }
    }

    override fun cleanUp() {
        coroutineScope.cancel()
    }
}