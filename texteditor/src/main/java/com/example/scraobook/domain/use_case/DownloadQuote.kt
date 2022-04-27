package com.example.scraobook.domain.use_case

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.scraobook.BuildConfig
import com.example.scraobook.R
import com.example.scraobook.util.CaptureScreen
import com.example.scraobook.util.ShareFile
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class DownloadQuote @Inject constructor(private val captureScreen: CaptureScreen,
                                        private val shareFile: ShareFile) {
    operator fun invoke(view:View?) {
        view?.let {
            var bitmap = captureScreen.viewToBitmap(it)
            bitmap.let { shareFile.bitmapToFile(view.context, bitmap)}
        }
     }
}