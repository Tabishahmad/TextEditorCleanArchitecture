package com.example.scraobook.domain.use_case.quoteDetailsUC

data class QuoteDetailsUseCases(val addText: AddText,
                                val addImage:AddImage,
                                val addFrame: AddFrame,
                                val addSticker: AddSticker,
                                val changeQuote: ChangeQuote,
                                val changeBackgroundColor: ChangeBackgroundColor,
                                val changeBackgroundImage: ChangeBackgroundImage)
