package com.example.itscproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class TeamMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_member)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation= 0F
        val viewModel = ViewModelProvider(this)[TeamMemberViewModel::class.java]
        viewModel.setID(intent.getStringExtra("ID"))
        val memberPhoto = findViewById<ImageView>(R.id.team_member_info_photo)
        val memberName = findViewById<TextView>(R.id.team_member_info_name)
        val memberRole = findViewById<TextView>(R.id.team_member_info_role)
        val memberEducation = findViewById<TextView>(R.id.team_member_info_education)
        val memberAdditionalInfo = findViewById<TextView>(R.id.team_member_info_additional_text)
        viewModel.getMemberSuccess.observe(this){success ->
            if(!success)
                finish()
        }
        viewModel.teamMember.observe(this){ teamMember ->
            memberName.text = getString(R.string.nameSurname, teamMember.name, teamMember.surname)
            memberRole.text = teamMember.role
            memberEducation.text = getString(R.string.education, teamMember.education)
            memberAdditionalInfo.text = getString(R.string.additional_info_circle, teamMember.additional)
        }
        viewModel.memberPhoto.observe(this){ photo ->
            memberPhoto.setImageBitmap(photo)
        }
    }
}