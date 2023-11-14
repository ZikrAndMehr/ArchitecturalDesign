package com.zam.architecturaldesign.fragments.new_quote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zam.architecturaldesign.MainActivity
import com.zam.architecturaldesign.data.Quote
import com.zam.architecturaldesign.databinding.FragmentNewQuoteBinding
import com.zam.architecturaldesign.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

class NewQuoteFragment : Fragment() {

    private var _binding: FragmentNewQuoteBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModel.getFactory((requireActivity() as MainActivity).quoteDataSource)
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

    private fun setupViews() {
        binding.btnSave.setOnClickListener { saveQuote() }
    }

    private fun saveQuote() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.apply {
                val quoteText = etQuote.text.toString()
                val quoteAuthor = etAuthor.text.toString()
                val quote = Quote(quoteText, quoteAuthor)
                sharedViewModel.insertQuote(quote)
                navigateToQuotesFragment()
            }
        }
    }

    private fun navigateToQuotesFragment() {
        findNavController().navigateUp()
    }
}