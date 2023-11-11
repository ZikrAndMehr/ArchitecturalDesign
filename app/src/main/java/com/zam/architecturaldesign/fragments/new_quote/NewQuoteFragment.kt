package com.zam.architecturaldesign.fragments.new_quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zam.architecturaldesign.MainActivity
import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.databinding.FragmentNewQuoteBinding
import com.zam.architecturaldesign.presenters.new_quote_presenter.NewQuoteContract
import com.zam.architecturaldesign.presenters.new_quote_presenter.NewQuotePresenter

class NewQuoteFragment : Fragment(), NewQuoteContract.ViewInterface {

    private var _binding: FragmentNewQuoteBinding? = null
    private val binding get() = _binding!!
    private val newQuotePresenter by lazy {
        NewQuotePresenter(this, (requireActivity() as MainActivity).quoteDataSource)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        newQuotePresenter.cleanUp()
    }

    private fun setupViews() {
        binding.btnSave.setOnClickListener { saveQuote() }
    }

    private fun saveQuote() {
        binding.apply {
            val quoteText = etQuote.text.toString()
            val quoteAuthor = etAuthor.text.toString()
            val quote = Quote(quoteText, quoteAuthor)
            newQuotePresenter.insertQuote(quote)
        }
    }

    private fun navigateToQuotesFragment() {
        findNavController().navigateUp()
    }

    override fun returnToQuotesFragment() {
        navigateToQuotesFragment()
    }
}