package com.example.itscproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.itscproject.R
import com.example.itscproject.TeamMemberActivity
import com.example.itscproject.model.TeamMember

class TeamMemberCardAdapter(
    private val dataset: List<TeamMember>
) : RecyclerView.Adapter<TeamMemberCardAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val teamMemberPhoto: ImageView = view.findViewById(R.id.team_member_photo)
        val teamMemberName: TextView = view.findViewById(R.id.team_member_name)
        val teamMemberRole: TextView = view.findViewById(R.id.team_member_role)
        val teamMemberEducation: TextView = view.findViewById(R.id.team_member_education)
        var id: Int = 0
        init {
            view.setOnClickListener {
                val intent = Intent(view.context,TeamMemberActivity::class.java)
                intent.putExtra("ID",id)
                startActivity(view.context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val teamMember = dataset[position]
        holder.id = teamMember.id
        holder.teamMemberPhoto.setImageResource(teamMember.imageResourceId)
        holder.teamMemberName.text = teamMember.name
        holder.teamMemberRole.text = teamMember.role
        holder.teamMemberEducation.text = teamMember.education
    }

    override fun getItemCount() = dataset.size
}