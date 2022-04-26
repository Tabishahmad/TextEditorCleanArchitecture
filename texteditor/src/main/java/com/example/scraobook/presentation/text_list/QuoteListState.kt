package com.example.scraobook.presentation.text_list

data class QuoteListState(
    val isLoading: Boolean = false,
    val data: List<String>? = null,
    val error: String = ""
)