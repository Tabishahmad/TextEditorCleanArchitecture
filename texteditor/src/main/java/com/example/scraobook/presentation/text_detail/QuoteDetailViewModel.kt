package com.example.scraobook.presentation.text_detail

import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import com.example.abner.stickerdemo.view.BubbleTextView
import com.example.abner.stickerdemo.view.StickerView
import com.example.scraobook.R
import com.example.scraobook.domain.use_case.quoteDetailsUC.QuoteDetailsUseCases
import com.example.scraobook.util.UrlToBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(private val useCases: QuoteDetailsUseCases) : ViewModel() {
    private val _seletedQuoteText = MutableStateFlow("This is default text")
    val seletedQuoteText: StateFlow<String> = _seletedQuoteText

//    val _newAddedText = MutableLiveData<String>()
    lateinit var _container : CardView
    fun setContainer(cardView: CardView){
        _container = cardView
    }
    fun updateSeletedQuotedText(currentQuote: String){
        _seletedQuoteText.value = currentQuote
    }

    fun addText(view: View){
        useCases.addText(view.context){it->
            val tv_sticker = BubbleTextView(view.context,Color.WHITE,0)
            tv_sticker.setImageResource(com.example.abner.stickerdemo.R.mipmap.bubble_7_rb)
            val lp = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            tv_sticker.setText(it)
            _container.addView(tv_sticker,lp)
        }
    }
    fun addImage(view: View){
        println("function click")
    }
    fun addFrame(view: View){
        println("function click")
    }
    fun addSticker(view: View){
        useCases.addSticker(view.context){it->
            UrlToBitmap.downloadBitmap(view.context,it){bitmap->
                bitmap.let {
                    val sticker = StickerView(view.context)
                    sticker.setBitmap(bitmap)
                    _container.addView(sticker)
                }
            }
        }
    }
    fun changeQuote(view: View){
        println("function click")
    }
    fun changeBackgroundColor(view: View){
        println("function click")
    }
    fun changeBackgroundImage(view: View){
        println("function click")
    }

}