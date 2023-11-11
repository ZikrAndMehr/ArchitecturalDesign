package com.zam.architecturaldesign.presenters.new_quote_presenter

import com.zam.architecturaldesign.data.Quote

object NewQuoteContract {

    interface PresenterInterface {

        fun insertQuote(quote: Quote)

        fun cleanUp()
    }

    interface ViewInterface {

        fun returnToQuotesFragment()
    }
}