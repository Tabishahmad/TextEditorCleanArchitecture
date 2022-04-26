package com.example.scraobook.domain.use_case

data class QuoteListUseCases(
    val getQuotes: GetQuotes,
    val downloadQuote: DownloadQuote,
    val shareQuotes: ShareQuotes,
    val shareQuotesOnWhatsApp: ShareQuotesOnWhatsApp)
