package com.example.scraobook.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

object UrlToBitmap {
    fun downloadBitmap(context: Context,url:String,callBack:(result:Bitmap?)->Unit){
        Glide.with(context).asBitmap().load(url).into(object :CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                callBack(resource)
            }
            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
                callBack(null)
            }
        })
    }
}