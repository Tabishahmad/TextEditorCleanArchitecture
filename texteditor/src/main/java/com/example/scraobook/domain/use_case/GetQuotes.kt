package com.example.scraobook.domain.use_case

import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.presentation.text_list.QuoteListState
import com.example.scraobook.util.Resource
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetQuotes @Inject constructor(private val repository: QuoteRepository) {
    @OptIn(InternalCoroutinesApi::class)
    operator fun invoke(category: String): Flow<Resource<List<String>>> = flow {
        try {
            repository.getQuotesList(category).collect {result->
                when(result){
                    is Resource.Success->{
                        emit(Resource.Success(data = result.data))
                    }
                    is Resource.Error->{

                    }
                    is Resource.Loading->{

                    }
                }
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }
}