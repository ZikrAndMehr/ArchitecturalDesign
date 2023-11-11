package com.zam.architecturaldesign.presenters.new_quote_presenter

import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.data.QuoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NewQuotePresenter(
    private val viewInterface: NewQuoteContract.ViewInterface,
    private val quoteDataSource: QuoteDataSource
) : NewQuoteContract.PresenterInterface {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    override fun insertQuote(quote: Quote) {
        coroutineScope.launch {
            quoteDataSource.insert(quote)
            viewInterface.returnToQuotesFragment()
        }
    }

    override fun cleanUp() {
        coroutineScope.cancel()
    }
}