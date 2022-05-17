package com.example.scraobook.presentation.text_detail

import android.content.Context
import com.example.scraobook.R
import com.example.scraobook.util.ColorPickerDialog
import javax.inject.Inject

class ColorPicker @Inject constructor() {
    fun showColorPickerDialog(context: Context,callback:(result:Int)->Unit){
        val colorPickerDialog = ColorPickerDialog(context,
            R.color.colorOrange, object : ColorPickerDialog.OnColorSelectedListener{
                override fun onColorSelected(color: Int) {
                    callback(color)
                }
        })
        colorPickerDialog.show()
    }
}