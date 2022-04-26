package com.example.scraobook.domain.repository

import com.example.scraobook.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun getQuotesList(
        path: String
    ): Flow<Resource<List<String>>>
}