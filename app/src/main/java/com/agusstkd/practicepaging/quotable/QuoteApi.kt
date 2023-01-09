package com.agusstkd.practicepaging.quotable

import com.agusstkd.practicepaging.quotable.models.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query


interface QuoteApi {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page: Int): QuoteList
}