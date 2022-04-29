package com.example.scraobook.hilt

import com.example.scraobook.data.remote.FirebaseTextFetchApi
import com.example.scraobook.data.repository.QuoteRepositoryImpl
import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.domain.use_case.quoteDetailsUC.*
import com.example.scraobook.domain.use_case.quoteListUC.*
import com.example.scraobook.util.ShareFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideQuoteDetailsUseCases(): QuoteDetailsUseCases {
        return QuoteDetailsUseCases(
            addText = AddText(),
            addImage = AddImage(),
            addFrame = AddFrame(),
            addSticker = AddSticker(),
            changeQuote = ChangeQuote(),
            changeBackgroundColor = ChangeBackgroundColor(),
            changeBackgroundImage = ChangeBackgroundImage()
        )
    }
}