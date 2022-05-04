package com.example.scraobook.data.repository

import com.example.scraobook.data.model.StickerFramesDTO
import com.example.scraobook.data.remote.StickerFrameApi
import com.example.scraobook.domain.repository.StickerFrameRepository

class StickerFrameRepositoryImpl(private val stickerFrameApi: StickerFrameApi) : StickerFrameRepository{
    override suspend fun StickerFrameList(): StickerFramesDTO {
        return stickerFrameApi.getFrameSticker()
    }
}