package com.example.mymansion.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MansionRetrofitClient {

    companion object {
        private const val BASE_URL = "http://my-json-server.typicode.com/Himuravidal/mansions/"

        fun retrofitInstance(): MansionApi {
            val retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(MansionApi::class.java)
        }
    }
}