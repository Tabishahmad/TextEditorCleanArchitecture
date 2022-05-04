package com.example.scraobook.hilt

import com.example.scraobook.data.remote.FirebaseTextFetchApi
import com.example.scraobook.data.remote.StickerFrameApi
import com.example.scraobook.data.repository.QuoteRepositoryImpl
import com.example.scraobook.data.repository.StickerFrameRepositoryImpl
import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.domain.repository.StickerFrameRepository
import com.example.scraobook.domain.use_case.quoteDetailsUC.*
import com.example.scraobook.domain.use_case.quoteListUC.*
import com.example.scraobook.util.Constants
import com.example.scraobook.util.ShareFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HIltModules {
    @Provides
    fun provideQuoteRepository(): QuoteRepository {
        return QuoteRepositoryImpl(firebaseTextFetchApi = FirebaseTextFetchApi())
    }
    @Provides
    @Singleton
    fun provideShareFile(): ShareFile{
        return ShareFile()
    }
    @Provides
    @Singleton
    fun provideQuotesUseCases(repository: QuoteRepository,shareFile: ShareFile): QuoteListUseCases {
        return QuoteListUseCases(
            getQuotes = GetQuotes(repository),
            saveQuote = SaveQuote(shareFile),
            shareQuotes = ShareQuotes(),
            shareQuotesOnWhatsApp = ShareQuotesOnWhatsApp()
        )
    }
    @Provides
    @Singleton
    fun provideQuoteDetailsUseCases(stickerFrameRepository: StickerFrameRepository): QuoteDetailsUseCases {
        return QuoteDetailsUseCases(
            addText = AddText(),
            addImage = AddImage(),
            addFrame = AddFrame(),
            addSticker = AddSticker(stickerFrameRepository),
            changeQuote = ChangeQuote(),
            changeBackgroundColor = ChangeBackgroundColor(),
            changeBackgroundImage = ChangeBackgroundImage()
        )

    }

    @Provides
    fun provideStickerFrameRepository(stickerFrameApi: StickerFrameApi): StickerFrameRepository {
        return StickerFrameRepositoryImpl(stickerFrameApi)
    }
    @Provides
    fun provideStickerFrameApi(): StickerFrameApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(StickerFrameApi::class.java)
    }
}