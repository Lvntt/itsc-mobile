package com.example.itscproject.network

import com.example.itscproject.data.TeamMemberPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiInterface {

    @GET("/api-guest/posts")
    fun getPosts() : Call<PostsResponse>

    @GET("/api-guest/post/photo/{id}")
    fun getPhoto(@Path("id") id: String):Call<TeamMemberPhoto>
}