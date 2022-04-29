package com.example.scraobook.domain.use_case.quoteListUC

import android.view.View
import androidx.core.view.drawToBitmap
import com.example.scraobook.util.ShareFile
import javax.inject.Inject

class SaveQuote @Inject constructor(private val shareFile: ShareFile) {
    operator fun invoke(view:View?,callback:(result:Boolean)->Unit) {
        view?.let {
            var bitmap = view.drawToBitmap()
            var result = bitmap.let { shareFile.bitmapToFile(view.context, bitmap)}
            callback(result)
        }
     }
}