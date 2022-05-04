package com.example.scraobook.domain.repository

import com.example.scraobook.data.model.StickerFramesDTO

interface StickerFrameRepository {
    suspend fun StickerFrameList(): StickerFramesDTO
}