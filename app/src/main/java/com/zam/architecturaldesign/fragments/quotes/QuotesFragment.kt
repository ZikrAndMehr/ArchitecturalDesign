package com.zam.architecturaldesign.fragments.quotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.zam.architecturaldesign.MainActivity
import com.zam.architecturaldesign.R
import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.databinding.FragmentQuotesBinding
import com.zam.architecturaldesign.presenters.quotes_presenter.QuotesContract
import com.zam.architecturaldesign.presenters.quotes_presenter.QuotesPresenter

class QuotesFragment : Fragment(), QuotesContract.ViewInterface {

    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!
    private val quotesPresenter by lazy {
        QuotesPresenter(this, (requireActivity() as MainActivity).quoteDataSource)
    }
    private val quotesAdapter by lazy { QuotesAdapter { quotesPresenter.deleteQuote(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    override fun onStart() {
        super.onStart()
        quotesPresenter.startCollectingFlows()
    }

    override fun onStop() {
        super.onStop()
        quotesPresenter.stopCollectingFlows()
    }

    override fun onDestroy() {
        super.onDestroy()
        quotesPresenter.cleanUp()
    }

    private fun setupViews() {
        binding.apply {
            rvQuotes.adapter = quotesAdapter
            fabAddQuote.setOnClickListener {
                navigateToQuoteFragment()
            }
        }
    }

    private fun navigateToQuoteFragment() {
        val directions = QuotesFragmentDirections.actionQuotesFragmentToNewQuoteFragment()
        findNavController().navigate(directions)
    }

    override fun updateQuotesAdapter(quotes: List<Quote>) {
        if (quotes.isEmpty()) {
            Toast.makeText(requireContext(), R.string.no_quotes, Toast.LENGTH_SHORT).show()
        } else {
            quotesAdapter.submitList(quotes)
        }
    }
}