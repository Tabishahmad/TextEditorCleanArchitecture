package com.example.scraobook.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.scraobook.BuildConfig
import com.example.scraobook.R
import java.io.File
import java.io.FileOutputStream

class ShareFile {
    fun bitmapToFile(context: Context, bitmap: Bitmap?) {
        try {
            val file = File(context.externalCacheDir, "share.png")
            val fOut = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true, false)
            shareImage(context, getProviderUri(context, file))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun shareImage(context: Context, uri: Uri?) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            var sAux = """
            Download ${context.getString(R.string.app_name)}
            
            """.trimIndent()
            sAux = """
            ${sAux}https://play.google.com/store/apps/details?id=${context.packageName}
            
            """.trimIndent()
            intent.putExtra(Intent.EXTRA_TEXT, sAux)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.type = "image/png"
            context.startActivity(Intent.createChooser(intent, "Share image via"))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
    private fun getProviderUri(ctx: Context?, file: File): Uri? {
        val filePath = file.absoluteFile
        return FileProvider.getUriForFile(
            ctx!!,
            BuildConfig.APPLICATION_ID + ".provider",
            filePath
        )
    }
}