package com.example.scraobook.presentation.text_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.scraobook.R
import com.example.scraobook.domain.use_case.quoteListUC.QuoteListUseCases
import com.example.scraobook.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(private val useCases: QuoteListUseCases) : ViewModel() {

    private val _quoteList = MutableStateFlow<QuoteListState>(QuoteListState())
    val quoteList: StateFlow<QuoteListState> = _quoteList

    fun getQuoteList(s: String) {
        useCases.getQuotes(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _quoteList.value = QuoteListState(isLoading = true)
                }
                is Resource.Success -> {
                    _quoteList.value = QuoteListState(data = it.data)
                }
                is Resource.Error -> {
                    _quoteList.value = QuoteListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
    /*
    view = view which is being clicked
    capturedView = view which is captured for share and save
    * */
    fun onQuoteItemClick(view: View, quote: String, index: Int,capturedView: View?){
        when(view.id){
            R.id.download->{
                useCases.saveQuote(capturedView) { result ->
                    println("callBack " + result)
                }
            }
            R.id.card->{
                var bundle = Bundle()
                bundle.putString("currentQuote",quote)
                view.findNavController().navigate(R.id.action_quoteList_to_qupteDetail,bundle)
            }
        }
    }
}


