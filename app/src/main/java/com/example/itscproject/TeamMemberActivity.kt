package com.example.itscproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TeamMemberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_member)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation= 0F
        val memberPhoto = findViewById<TextView>(R.id.team_member_info_photo)
        val memberName = findViewById<TextView>(R.id.team_member_info_name)
        val memberRole = findViewById<TextView>(R.id.team_member_info_role)
        val memberEducation = findViewById<TextView>(R.id.team_member_info_education)
        val memberAdditionalInfo = findViewById<TextView>(R.id.team_member_info_additional_text)
        memberName.text = getString(R.string.nameSurname, intent.getStringExtra("name"), intent.getStringExtra("surname"))
        memberRole.text = intent.getStringExtra("role")
        memberEducation.text = getString(R.string.education, intent.getStringExtra("education"))
        memberAdditionalInfo.text = getString(R.string.additional_info_circle, intent.getStringExtra("additional"))
    }
}