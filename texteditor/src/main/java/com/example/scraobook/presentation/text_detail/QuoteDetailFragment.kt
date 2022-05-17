package com.example.scraobook.presentation.text_detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.scraobook.R
import com.example.scraobook.databinding.QuoteDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteDetailFragment : Fragment() {
    private val quoteDetailViewModel: QuoteDetailViewModel by viewModels()
    private lateinit var b: QuoteDetailFragmentBinding
    private lateinit var selectedQuote :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedQuote = arguments?.getString("currentQuote")!!
        quoteDetailViewModel.updateSeletedQuotedText(selectedQuote)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = QuoteDetailFragmentBinding.inflate(inflater, container, false)
        b.viewModel = quoteDetailViewModel

        attachNewViews()
        deleteAddedView()

        return b.root
    }
    private fun attachNewViews(){
        viewLifecycleOwner.lifecycleScope.launch {
            quoteDetailViewModel.sharedFlowAddAny.collect {
                if(it is View) {
                    b.container.addView(it)
                }else if(it is Int){
                    changeBackgroundColor(it)
                }
            }
        }
    }
    private fun deleteAddedView(){
        viewLifecycleOwner.lifecycleScope.launch {
            quoteDetailViewModel.sharedFlowDeleteView.collect {
                b.container.removeView(it)
            }
        }
    }
    private fun changeBackgroundColor(color:Int){
        b.container.setBackgroundColor(color)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.text_edit_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.saveImage -> {
                 quoteDetailViewModel.saveImage(requireContext(),b.container)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}