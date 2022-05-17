package com.example.scraobook.util

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.view.View
import androidx.core.view.drawToBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import javax.inject.Inject


class SaveViewAsBitmap @Inject constructor() {
    operator fun invoke(context: Context, view: View?): String {
        val bitmap: Bitmap? = view?.drawToBitmap(Bitmap.Config.ARGB_8888)
        var string : String = ""
        bitmap?.let {
            string = saveImage(context, bitmap)
        }
        println(" SaveViewAsBitmap  1  " + string)
        return string
    }
    private fun saveImage(context: Context, bitmap: Bitmap): String {
        return try {
            val output: OutputStream
            val cal: Calendar = Calendar.getInstance()
            val filepath: File? = context.getExternalFilesDir(null)
            // Create a new folder in SD Card
            val dir = File(filepath?.absolutePath.toString() + "/Collection")
            if (!dir.exists()) dir.mkdirs()
            val mImagename = "image_" + cal.getTimeInMillis().toString() + ".png"
            // Create a name for the saved image
            val file = File(dir, mImagename)
            MediaScannerConnection.scanFile(
                context, arrayOf(file.getPath()),
                null
            ) { path, uri ->
                // now visible in gallery
            }
            output = FileOutputStream(file)
            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
            output.flush()
            output.close()
            return file.path
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }
}