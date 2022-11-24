package com.example.itscproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itscproject.adapter.TeamMemberCardAdapter
import com.example.itscproject.databinding.FragmentHomeBinding
import com.example.itscproject.requests.PostsApiClient
import com.example.itscproject.requests.PostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel =
        //    ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.teamMembers
        recyclerView.layoutManager = LinearLayoutManager(root.context)

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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}