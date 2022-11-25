package com.example.itscproject.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.itscproject.R
import com.example.itscproject.TeamMemberActivity
import com.example.itscproject.data.TeamMember

class TeamMemberCardAdapter(
    private val dataset: List<TeamMember>
) : RecyclerView.Adapter<TeamMemberCardAdapter.ItemViewHolder>() {
    private var memberPhotos: Map<String, Bitmap> = emptyMap()
    private var boundViewHolders: HashSet<ItemViewHolder> = HashSet()
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var teamMember: TeamMember? = null
        val teamMemberPhoto: ImageView = view.findViewById(R.id.team_member_photo)
        val teamMemberName: TextView = view.findViewById(R.id.team_member_name)
        val teamMemberRole: TextView = view.findViewById(R.id.team_member_role)
        val teamMemberEducation: TextView = view.findViewById(R.id.team_member_education)

        init {
            view.setOnClickListener {
                //val intent = Intent(view.context,TeamMemberActivity::class.java)
                //startActivity(view.context, intent, null)
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
        holder.teamMember = teamMember
        if(memberPhotos[teamMember.id] != null){
            holder.teamMemberPhoto.setImageBitmap(memberPhotos[teamMember.id])
        }
        holder.teamMemberName.text = teamMember.name
        holder.teamMemberRole.text = teamMember.role
        holder.teamMemberEducation.text = teamMember.education
        boundViewHolders.add(holder)
    }

    override fun onViewRecycled(holder: ItemViewHolder) {
        boundViewHolders.remove(holder)
        super.onViewRecycled(holder)
    }

    fun notifyImageSetChanged(imageSet: Map<String, Bitmap>){
        memberPhotos=imageSet
        for(holder in boundViewHolders){
            if(memberPhotos[holder.teamMember?.id]!=null){
                holder.teamMemberPhoto.setImageBitmap(memberPhotos[holder.teamMember?.id])
            }
        }
    }
    override fun getItemCount() = dataset.size
}