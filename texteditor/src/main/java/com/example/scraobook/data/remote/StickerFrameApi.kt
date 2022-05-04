package com.example.scraobook.data.remote

import com.example.scraobook.data.model.StickerFramesDTO
import retrofit2.http.GET

interface StickerFrameApi {
    @GET("file/0tn8muvffjzq3kp/textEditor.txt/file")
    suspend fun getFrameSticker() : StickerFramesDTO
}