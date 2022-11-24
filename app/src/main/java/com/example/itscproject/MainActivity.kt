package com.example.itscproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import com.example.itscproject.adapter.TeamMemberCardAdapter
import com.example.itscproject.requests.PostsApiClient
import com.example.itscproject.requests.PostsResponse
import retrofit2.Call
import retrofit2.Response

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val call = PostsApiClient.apiClient.getPosts()

        call.enqueue(object: Callback<PostsResponse> {
            override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
                val teamMembers = response.body()!!.data
                recyclerView.adapter = TeamMemberCardAdapter(teamMembers)
            }

            override fun onFailure(call: Call<PostsResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

        })
    }
}