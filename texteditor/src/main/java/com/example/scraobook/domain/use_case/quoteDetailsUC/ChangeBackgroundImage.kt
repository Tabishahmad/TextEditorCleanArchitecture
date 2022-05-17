package com.example.scraobook.domain.use_case.quoteDetailsUC


import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.scraobook.presentation.text_detail.StickerNFrameDialog
import javax.inject.Inject

class ChangeBackgroundImage @Inject constructor(private val fragmentManager: FragmentManager) {

    operator fun invoke(callback:(result:String)->Unit){
        val bundle = Bundle()
        bundle.putInt("content_type", ContentType_BG_Texture)
        val stickerNFrameDialog = StickerNFrameDialog.newInstance(){ result ->
            callback(result)
        }
        stickerNFrameDialog.arguments = bundle
        stickerNFrameDialog.show(fragmentManager,"")
    }
}