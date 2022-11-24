package com.example.itscproject.requests

import retrofit2.Call
import retrofit2.http.GET

interface PostsApiInterface {

    @GET("/api-guest/posts")
    fun getPosts() : Call<PostsResponse>
}