package com.example.scraobook.domain.use_case.quoteDetailsUC

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scraobook.R
import com.example.scraobook.data.model.StickerFramesDTO
import com.example.scraobook.data.model.toDomainSticker
import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.domain.repository.StickerFrameRepository
import com.example.scraobook.presentation.text_detail.QuoteDetailViewModel
import com.example.scraobook.presentation.text_detail.StickersAdapter
import com.example.scraobook.util.RecyclerViewClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddSticker @Inject constructor(private val stickerFrameRepository: StickerFrameRepository) {

    operator fun invoke(context: Context){
        showStickersDialog(context,true)
    }
    private fun showStickersDialog(context: Context, isCancelable: Boolean) {
        val dialog = Dialog(context, R.style.Theme_Dialog)
        dialog.setCancelable(isCancelable)
        dialog.setCanceledOnTouchOutside(isCancelable)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.show_stickers)
        val stickersRv: RecyclerView = dialog.findViewById(R.id.rv)
        val gridLayoutManager = GridLayoutManager(context, 5)
        stickersRv.layoutManager = gridLayoutManager

        GlobalScope.launch {
            // For YB
            // yahan data le ya data load kre viewModel me aur isko pass kare
            // ye function bhi viewmodel se hi click ho raha hai
            // mere hisab se viewmodel me hi lena chaye lekin calling me ye easy hai aur dusra chiz
            // wahan data lete hai to check krna hoga ki data aaya ya nahi then button click ho
            val data = stickerFrameRepository.StickerFrameList()
            val domainData = data.toDomainSticker()
            if(domainData != null){
                val listener : RecyclerViewClickListener = object : RecyclerViewClickListener{
                    override fun onClick(view: View?, position: Int) {

                    }
                }
                withContext(Dispatchers.Main) {
                    stickersRv.adapter = StickersAdapter(
                        domainData.sticker as java.util.ArrayList<String>?,
                        listener
                    )
                }
            }
        }
        dialog.show()
    }
}