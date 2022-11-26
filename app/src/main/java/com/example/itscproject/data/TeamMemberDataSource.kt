package com.example.itscproject.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.itscproject.network.PostsApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TeamMemberDataSource {

    private const val BASE_URL = "http://45.141.78.192:8000/"

    private val apiClient: PostsApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(PostsApiInterface::class.java)
    }

    fun getTeamMembers(): List<TeamMember>?{
        val call = apiClient.getPosts()
        return call.execute().body()?.data
    }

    fun getPhoto(id: String): Bitmap?{
        val call = apiClient.getPhoto(id)
        var base64 = call.execute().body()?.photo
        return if(base64!=null){
            base64=base64.split(',')[1]
            val byteArray = Base64.decode(base64, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
        } else{
            null
        }
    }
}