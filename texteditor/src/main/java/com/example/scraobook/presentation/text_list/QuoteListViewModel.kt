package com.example.scraobook.presentation.text_list

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scraobook.R
import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.domain.use_case.QuoteListUseCases
import com.example.scraobook.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    fun onQuoteItemClick(clickedView: View, quote: String, index: Int,capturedView: View?){
        when(clickedView.id){
            R.id.download->{
                useCases.downloadQuote(capturedView)
            }
        }
    }
}


