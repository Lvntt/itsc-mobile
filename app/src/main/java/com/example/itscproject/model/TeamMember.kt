package com.example.itscproject.model

import androidx.annotation.DrawableRes

data class TeamMember(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val role: String,
    val education: String,
    val additionalInfo: String
)