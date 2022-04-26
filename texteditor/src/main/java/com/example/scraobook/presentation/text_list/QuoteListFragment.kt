package com.example.scraobook.presentation.text_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
import com.example.scraobook.databinding.QuoteListFragmentBinding
import java.util.ArrayList

@AndroidEntryPoint
class QuoteListFragment : Fragment(),QuotesListAdapter.QuotesItemClickListener {

    private val quoteListViewModel: QuoteListViewModel by viewModels()
    private lateinit var b: QuoteListFragmentBinding
    private var catName: String? = null
    companion object{
       fun newInstance() = QuoteListFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            catName = it.getString("name")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        b = QuoteListFragmentBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindRvWithLiveData()
        getQuotesListData()
    }
    private fun getQuotesListData(){
        quoteListViewModel.getQuoteList("")
    }
    private fun bindRvWithLiveData(){
        GlobalScope.launch(Dispatchers.Main) {
            quoteListViewModel.quoteList.collect { it ->
                it.data?.let {
                    println("value from firebase QuoteListFragment "+ it)
                    setQuotesList( it as ArrayList<String>?,20F)
            } }
        }
    }
    private fun setQuotesList(list: List<String>?,textSize: Float) {
        b.pb.visibility = View.GONE
        if (list != null && list.isNotEmpty()) {
            b.rv.setTextSize(textSize)
            b.rv.setList(list)
            b.rv.setListener(this)
        } else {
            b.retry.root.visibility = View.VISIBLE
            b.retry.btn.setOnClickListener {
                catName?.let { catName ->
                    b.retry.root.visibility = View.GONE
                    b.pb.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onQuoteItemClick(v: View, quote: String, index: Int) {
        
    }
}