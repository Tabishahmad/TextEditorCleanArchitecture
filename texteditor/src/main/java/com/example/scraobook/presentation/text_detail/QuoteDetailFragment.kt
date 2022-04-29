package com.example.scraobook.presentation.text_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.scraobook.R
import com.example.scraobook.databinding.QuoteDetailFragmentBinding
import com.example.scraobook.databinding.QuoteListFragmentBinding
import com.example.scraobook.presentation.text_list.QuoteListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteDetailFragment : Fragment() {

//    companion object {
//        fun newInstance() = QuoteDetailFragment()
//    }
    private val quoteDetailViewModel: QuoteDetailViewModel by viewModels()
    private lateinit var b: QuoteDetailFragmentBinding
    private lateinit var selectedQuote :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedQuote = arguments?.getString("currentQuote")!!
        quoteDetailViewModel.updateSeletedQuotedText(selectedQuote)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = QuoteDetailFragmentBinding.inflate(inflater, container, false)
        b.viewModel = quoteDetailViewModel
        return b.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}