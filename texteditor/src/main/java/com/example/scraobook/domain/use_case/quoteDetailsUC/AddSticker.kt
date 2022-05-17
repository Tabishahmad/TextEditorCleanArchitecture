package com.example.scraobook.domain.use_case.quoteDetailsUC

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.scraobook.presentation.text_detail.StickerNFrameDialog
import javax.inject.Inject
const val ContentType_Sticker = 1;
const val ContentType_Frame = 2;
const val ContentType_BG_Texture = 3;

class AddSticker @Inject constructor(private val fragmentManager: FragmentManager) {

    operator fun invoke(context: Context,callback:(result:String)->Unit){
        val bundle = Bundle()
        bundle.putInt("content_type", ContentType_Sticker)
        val stickerNFrameDialog = StickerNFrameDialog.newInstance(){result ->
            callback(result)
        }
        stickerNFrameDialog.arguments = bundle
        stickerNFrameDialog.show(fragmentManager,"")
    }
}