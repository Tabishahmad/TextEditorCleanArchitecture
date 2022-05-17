package com.example.scraobook.presentation.text_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scraobook.data.model.toDomainMessage
import com.example.scraobook.databinding.QuoteDialogLayoutBinding
import com.example.scraobook.util.RecyclerViewClickListener
import com.example.scraobook.util.Resource
import com.motivationalgif.imagesnquotes.view.quotes.QuotesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class QuotesDialog(private val callback: (result: String) -> Unit) : DialogFragment() {

    private lateinit var viewModel: StickerNFrameViewModel
    private lateinit var _binding: QuoteDialogLayoutBinding
    lateinit var arrayList: ArrayList<String>

    companion object {
        fun newInstance(callback: (result: String) -> Unit): QuotesDialog {
            val fragment = QuotesDialog(callback)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StickerNFrameViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = QuoteDialogLayoutBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCancelable(isCancelable)
        val linearLayoutManager = LinearLayoutManager(context)
        _binding.recycleView.layoutManager = linearLayoutManager

        val listener: RecyclerViewClickListener =
            RecyclerViewClickListener { view, position ->
                var selectedStickerURL = arrayList.get(position)
                callback(selectedStickerURL)
                dismiss()
        }

        GlobalScope.launch {
            viewModel.getStickerNFrame().collect { result ->
                when (result) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val data = result.data
                        arrayList = data?.toDomainMessage()!!
                        println("domainData " + arrayList)
                        withContext(Dispatchers.Main) {
                            val quotesAdapter = QuotesAdapter(listener)
                            _binding.recycleView.adapter =quotesAdapter
                            quotesAdapter.setList(arrayList)

                            _binding.progressCircular.visibility = View.GONE
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }
}