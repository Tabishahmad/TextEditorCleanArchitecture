package com.example.scraobook.domain.repository

import com.example.scraobook.data.model.StickerFramesDTO
import javax.inject.Inject

interface StickerFrameRepository{
    suspend fun stickerFrameList(): StickerFramesDTO
}