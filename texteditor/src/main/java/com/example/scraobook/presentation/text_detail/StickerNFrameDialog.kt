package com.example.scraobook.presentation.text_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.scraobook.data.model.toDomainBGTexture
import com.example.scraobook.data.model.toDomainFrame
import com.example.scraobook.data.model.toDomainSticker
import com.example.scraobook.databinding.ShowStickersBinding
import com.example.scraobook.domain.use_case.quoteDetailsUC.ContentType_BG_Texture
import com.example.scraobook.domain.use_case.quoteDetailsUC.ContentType_Frame
import com.example.scraobook.domain.use_case.quoteDetailsUC.ContentType_Sticker
import com.example.scraobook.util.RecyclerViewClickListener
import com.example.scraobook.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class StickerNFrameDialog(private val callback: (result: String) -> Unit) : DialogFragment() {

    private lateinit var viewModel: StickerNFrameViewModel
    private lateinit var _binding: ShowStickersBinding
    lateinit var arrayList: ArrayList<String>
    var content_type : Int = 0
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

    companion object {
        fun newInstance(callback: (result: String) -> Unit): StickerNFrameDialog {
            val fragment = StickerNFrameDialog(callback)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StickerNFrameViewModel::class.java)
        if (getArguments() != null) {
            content_type = requireArguments().getInt("content_type")
            println(" header " + content_type)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShowStickersBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCancelable(isCancelable)
        val gridLayoutManager = GridLayoutManager(context, 5)
        _binding.rv.layoutManager = gridLayoutManager
        val listener: RecyclerViewClickListener = object : RecyclerViewClickListener {
            override fun onClick(view: View?, position: Int) {
                var selectedStickerURL = arrayList.get(position)
                callback(selectedStickerURL)
                dismiss()
            }
        }
        GlobalScope.launch {
            viewModel.getStickerNFrame().collect { result ->
                when (result) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val data = result.data
                        if(content_type == ContentType_Sticker) {
                            arrayList = data?.toDomainSticker()!!
                        }else if(content_type == ContentType_Frame) {
                            arrayList = data?.toDomainFrame()!!
                        }else if(content_type == ContentType_BG_Texture) {
                            arrayList = data?.toDomainBGTexture()!!
                        }
                        println("domainData " + arrayList)
                        withContext(dispatcher) {
                            _binding.rv.adapter = StickersAdapter(
                                arrayList,
                                listener
                            )
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