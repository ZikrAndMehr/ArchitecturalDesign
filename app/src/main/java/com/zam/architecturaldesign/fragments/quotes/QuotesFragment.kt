package com.zam.architecturaldesign.fragments.quotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.zam.architecturaldesign.MainActivity
import com.zam.architecturaldesign.R
import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.databinding.FragmentQuotesBinding
import kotlinx.coroutines.launch

class QuotesFragment : Fragment() {

    private var _binding: FragmentQuotesBinding? = null
    private val binding get() = _binding!!
    private val quoteDataSource by lazy { (requireActivity() as MainActivity).quoteDataSource }
    private val quotesAdapter = QuotesAdapter { deleteQuote(it) }

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
        collectFlows()
    }

    private fun setupViews() {
        binding.apply {
            rvQuotes.adapter = quotesAdapter
            fabAddQuote.setOnClickListener {
                navigateToQuoteFragment()
            }
        }
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                quoteDataSource.allQuotes.collect {
                    if (it.isEmpty()) {
                        Toast.makeText(
                            requireContext(), R.string.no_quotes, Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        quotesAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun deleteQuote(quote: Quote) {
        viewLifecycleOwner.lifecycleScope.launch {
            quoteDataSource.delete(quote)
        }
    }

    private fun navigateToQuoteFragment() {
        val directions = QuotesFragmentDirections.actionQuotesFragmentToNewQuoteFragment()
        findNavController().navigate(directions)
    }
}