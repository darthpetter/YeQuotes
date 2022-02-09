package com.example.petterjose

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getYeQuote(@Url url: String) : Call<YeQuoteResponse>
}