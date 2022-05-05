package com.example.scraobook.presentation.text_detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.abner.stickerdemo.R
import com.example.abner.stickerdemo.view.BubbleTextView
import com.example.scraobook.databinding.QuoteDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteDetailFragment : Fragment() {
    private val quoteDetailViewModel: QuoteDetailViewModel by viewModels()
    private lateinit var b: QuoteDetailFragmentBinding
    private lateinit var selectedQuote :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedQuote = arguments?.getString("currentQuote")!!
        quoteDetailViewModel.updateSeletedQuotedText(selectedQuote)

//        mTv_text = TextView(requireContext())
//        mTv_text?.textSize = 50f
//        mTv_text?.gravity = Gravity.CENTER
//        mTv_text?.setOnTouchListener(Onto)

        quoteDetailViewModel._newAddedText.observe(requireActivity(), {it->
            val tv_sticker = BubbleTextView(requireContext(),Color.WHITE,0)
            tv_sticker.setImageResource(R.mipmap.bubble_7_rb)
            val lp = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            tv_sticker.setText(it)
            b.container.addView(tv_sticker,lp)
        })
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