package com.example.scraobook.hilt

import com.example.scraobook.data.remote.FirebaseTextFetchApi
import com.example.scraobook.data.repository.QuoteRepositoryImpl
import com.example.scraobook.domain.repository.QuoteRepository
import com.example.scraobook.domain.use_case.*
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
    fun provideQuotesUseCases(repository: QuoteRepository): QuoteListUseCases {
        return QuoteListUseCases(
            getQuotes = GetQuotes(repository),
            downloadQuote = DownloadQuote(),
            shareQuotes = ShareQuotes(),
            shareQuotesOnWhatsApp = ShareQuotesOnWhatsApp()
        )
    }
}