package com.example.scraobook.domain.use_case.quoteDetailsUC

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import com.example.scraobook.R

class AddImage {
    operator fun invoke(context: Context,
                        contact_gallery: ActivityResultLauncher<String>,
                        contact_camera: ActivityResultLauncher<Uri>,
                        cameraImgUri: Uri,
                        callback:(result:String)->Unit){
        selectImageChooseDialog(context,contact_gallery,contact_camera,cameraImgUri)
    }
    fun selectImageChooseDialog(context: Context,
                                contact: ActivityResultLauncher<String>,
                                contact_camera: ActivityResultLauncher<Uri>,
                                cameraImgUri: Uri) {
        val dialog = Dialog(context, R.style.Theme_Dialog)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.select_image_dialog)
        val fromGallery: ImageView = dialog.findViewById<ImageView>(R.id.fromGallery)
        val fromCamera: ImageView = dialog.findViewById<ImageView>(R.id.fromCamera)
        fromGallery.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                contact.launch("image/*")
                dialog.dismiss()
            }
        })
        fromCamera.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                contact_camera.launch(cameraImgUri)
            }
        })
        dialog.show()
    }

}