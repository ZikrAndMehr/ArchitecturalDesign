package com.zam.architecturaldesign.presenters.quotes_presenter

import com.zam.architecturaldesign.data.Quote

object QuotesContract {

    interface PresenterInterface {

        fun startCollectingFlows()

        fun stopCollectingFlows()

        fun deleteQuote(quote: Quote)

        fun cleanUp()
    }

    interface ViewInterface {

        fun updateQuotesAdapter(quotes: List<Quote>)
    }
}