package com.zam.architecturaldesign.fragments.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.databinding.QuoteHolderBinding

class QuotesAdapter(
    private val deleteQuoteFunction: (quote: Quote) -> Unit
) : ListAdapter<Quote, QuotesAdapter.QuoteHolder>(QuotesDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder {
        val binding = QuoteHolderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return QuoteHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        val quote = getItem(position)
        holder.bind(quote)
    }

    inner class QuoteHolder(
        private val binding: QuoteHolderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: Quote) {
            binding.apply {
                tvQuote.text = quote.quoteText
                tvAuthor.text = quote.quoteAuthor
                btnDelete.setOnClickListener {
                    deleteQuoteFunction(quote)
                }
            }
        }
    }

    class QuotesDiffItemCallback : DiffUtil.ItemCallback<Quote>() {

        override fun areItemsTheSame(oldItem: Quote, newItem: Quote) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
    }
}