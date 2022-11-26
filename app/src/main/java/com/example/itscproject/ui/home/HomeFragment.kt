package com.example.itscproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itscproject.R
import com.example.itscproject.adapter.TeamMemberCardAdapter
import com.example.itscproject.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.teamMembers
        recyclerView.layoutManager = LinearLayoutManager(root.context)

        homeViewModel.getIsSuccessful.observe(viewLifecycleOwner){ isSuccessful ->
            if(!isSuccessful){
                Snackbar.make(root, R.string.data_error, Snackbar.LENGTH_LONG).show()
            }
        }
        homeViewModel.teamMembers.observe(viewLifecycleOwner){ teamMembers ->
            if(recyclerView.adapter == null) {
                recyclerView.adapter = TeamMemberCardAdapter(teamMembers)
            } else {
                (recyclerView.adapter as TeamMemberCardAdapter).changeDataset(teamMembers)
            }
            homeViewModel.memberPhotos.value?.let {
                (recyclerView.adapter as TeamMemberCardAdapter).notifyImageSetChanged(
                    it
                )
            }
        }
        homeViewModel.memberPhotos.observe(viewLifecycleOwner){ photos ->
            (recyclerView.adapter as TeamMemberCardAdapter).notifyImageSetChanged(photos)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}