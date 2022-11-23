package com.example.itscproject

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.itscproject.R
import android.widget.TextView
import com.example.itscproject.data.DataSource

class TeamMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_member)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation= 0F
        val id = intent.getIntExtra("ID", -1)
        val memberPhoto = findViewById<ImageView>(R.id.team_member_info_photo)
        val memberName = findViewById<TextView>(R.id.team_member_info_name)
        val memberRole = findViewById<TextView>(R.id.team_member_info_role)
        val education = findViewById<TextView>(R.id.team_member_info_education)
        val additionalInfo = findViewById<TextView>(R.id.team_member_info_additional_text)
        memberPhoto.setImageResource(DataSource.teamMembers[id].imageResourceId)
        memberName.text = DataSource.teamMembers[id].name
        memberRole.text = DataSource.teamMembers[id].role
        education.text = getString(R.string.education,DataSource.teamMembers[id].education)
        additionalInfo.text = getString(R.string.additional_info_circle, DataSource.teamMembers[id].additionalInfo)
    }
}