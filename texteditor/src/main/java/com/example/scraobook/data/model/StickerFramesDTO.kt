package com.example.scraobook.data.model

import com.example.scraobook.domain.model.Sticker

data class StickerFramesDTO(
    val Message: List<String>,
    val frame: List<String>,
    val sm_id: Int,
    val sticker: List<String>,
    val ver: Int
)
fun StickerFramesDTO.toDomainSticker(): ArrayList<String>{
    return ArrayList(sticker)
}
fun StickerFramesDTO.toDomainFrame(): ArrayList<String>{
    return ArrayList(frame)
}
fun StickerFramesDTO.toDomainBGTexture(): ArrayList<String>{
    return ArrayList(frame)
}
fun StickerFramesDTO.toDomainMessage(): ArrayList<String>{
    return ArrayList(Message)
}