package com.example.itscproject.requests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsApiClient {

    const val BASE_URL = "http://45.141.78.192:8000/"

    val apiClient: PostsApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(PostsApiInterface::class.java)
    }
}