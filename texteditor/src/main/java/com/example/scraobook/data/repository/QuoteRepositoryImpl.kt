package com.example.scraobook.data.repository

import com.example.scraobook.data.remote.FirebaseTextFetchApi
import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.util.Resource
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.io.File
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class QuoteRepositoryImpl @Inject constructor(val firebaseTextFetchApi: FirebaseTextFetchApi):QuoteRepository {

    override suspend fun getQuotesList(path: String): Flow<Resource<List<String>>> {
        return flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(data = firebaseTextFetchApi.loadTextFromFirebase(path)))
        }
    }
}

