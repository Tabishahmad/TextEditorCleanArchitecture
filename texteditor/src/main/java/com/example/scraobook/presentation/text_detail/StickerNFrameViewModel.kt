package com.example.scraobook.presentation.text_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scraobook.data.model.StickerFramesDTO
import com.example.scraobook.domain.repository.StickerFrameRepository
import com.example.scraobook.presentation.text_list.QuoteListState
import com.example.scraobook.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StickerNFrameViewModel @Inject constructor(private val stickerFrameRepository:StickerFrameRepository):ViewModel() {
    suspend fun getStickerNFrame(): Flow<Resource<StickerFramesDTO>> {
        return flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(data = stickerFrameRepository.stickerFrameList()))
        }
    }
}