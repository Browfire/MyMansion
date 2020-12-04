package com.example.mymansion.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MansionApi {

    @GET("mansions/")
    suspend fun fetchAllMansions(): Response<List<MansionItem>>

    @GET("details/{id}")
    suspend fun fetchOneMansion(@Path("id") id: Int): Response<MansionDetails>

}