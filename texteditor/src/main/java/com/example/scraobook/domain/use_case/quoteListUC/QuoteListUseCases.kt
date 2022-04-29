package com.example.scraobook.domain.use_case.quoteListUC

data class QuoteListUseCases(
    val getQuotes: GetQuotes,
    val saveQuote: SaveQuote,
    val shareQuotes: ShareQuotes,
    val shareQuotesOnWhatsApp: ShareQuotesOnWhatsApp
)
