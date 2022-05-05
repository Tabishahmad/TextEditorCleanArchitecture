package com.example.scraobook.presentation.text_detail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scraobook.domain.use_case.quoteDetailsUC.QuoteDetailsUseCases
import com.example.scraobook.domain.use_case.quoteListUC.QuoteListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(private val useCases: QuoteDetailsUseCases) : ViewModel() {
    private val _seletedQuoteText = MutableStateFlow("This is default text")
    val seletedQuoteText: StateFlow<String> = _seletedQuoteText

    val _newAddedText = MutableLiveData<String>()

    fun updateSeletedQuotedText(currentQuote: String){
        _seletedQuoteText.value = currentQuote
    }

    fun addText(view: View){
        useCases.addText(view.context){it->
            _newAddedText.value = it
        }
    }
    fun addImage(view: View){
        println("function click")
    }
    fun addFrame(view: View){
        println("function click")
    }
    fun addSticker(view: View){
        useCases.addSticker(view.context)
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