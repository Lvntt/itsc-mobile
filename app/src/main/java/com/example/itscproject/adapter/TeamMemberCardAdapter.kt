package com.example.itscproject.adapter

import android.annotation.SuppressLint
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
    private var dataset: List<TeamMember>
) : RecyclerView.Adapter<TeamMemberCardAdapter.ItemViewHolder>() {
    private var memberPhotos: Map<String, Bitmap> = emptyMap()
    private var boundViewHolders: HashSet<ItemViewHolder> = HashSet()
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var teamMemberId: String? = null
        val teamMemberPhoto: ImageView = view.findViewById(R.id.team_member_photo)
        val teamMemberName: TextView = view.findViewById(R.id.team_member_name)
        val teamMemberRole: TextView = view.findViewById(R.id.team_member_role)
        val teamMemberEducation: TextView = view.findViewById(R.id.team_member_education)

        init {
            view.setOnClickListener {
                val intent = Intent(view.context,TeamMemberActivity::class.java)
                intent.putExtra("ID",teamMemberId)
                startActivity(view.context, intent, null)
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
        holder.teamMemberId = teamMember.id
        if(memberPhotos[teamMember.id] != null){
            holder.teamMemberPhoto.setImageBitmap(memberPhotos[teamMember.id])
            holder.teamMemberPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            holder.teamMemberPhoto.setImageResource(R.drawable.user_placeholder)
            holder.teamMemberPhoto.scaleType = ImageView.ScaleType.FIT_CENTER
        }
        holder.teamMemberName.text = holder.itemView.context.getString(R.string.nameSurname, teamMember.name, teamMember.surname)
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
            if(memberPhotos[holder.teamMemberId]!=null){
                holder.teamMemberPhoto.setImageBitmap(memberPhotos[holder.teamMemberId])
                holder.teamMemberPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeDataset(newDataset: List<TeamMember>){
        dataset = newDataset
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataset.size
}