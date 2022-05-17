package com.example.scraobook.domain.use_case.quoteDetailsUC

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.scraobook.presentation.text_detail.QuotesDialog
import com.example.scraobook.presentation.text_detail.StickerNFrameDialog
import javax.inject.Inject

class ChangeQuote @Inject constructor(private val fragmentManager: FragmentManager) {
    operator fun invoke(callback:(result:String)->Unit){
        QuotesDialog.newInstance(){ result ->
            callback(result)
        }.show(fragmentManager,"")
    }
}