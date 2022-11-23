package com.example.itscproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.itscproject.adapter.TeamMemberCardAdapter
import com.example.itscproject.data.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamMemberDataset = DataSource.teamMembers

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = TeamMemberCardAdapter(teamMemberDataset)
    }
}