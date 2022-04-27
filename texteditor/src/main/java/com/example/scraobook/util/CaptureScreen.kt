package com.example.scraobook.util

import android.graphics.Bitmap
import android.view.View

class CaptureScreen {
    fun viewToBitmap(view: View): Bitmap? {
        view.isDrawingCacheEnabled = true
        val bmp = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return bmp
    }
}