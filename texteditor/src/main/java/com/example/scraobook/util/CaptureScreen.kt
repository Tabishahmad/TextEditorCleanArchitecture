package com.example.scraobook.util

import android.graphics.Bitmap
import android.view.View
import androidx.core.view.drawToBitmap

class CaptureScreen {
    fun viewToBitmap(view: View): Bitmap? {
        view.drawToBitmap()
        view.isDrawingCacheEnabled = true
        val bmp = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return bmp
    }
}