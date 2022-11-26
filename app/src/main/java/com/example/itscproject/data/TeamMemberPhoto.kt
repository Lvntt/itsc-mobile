package com.example.itscproject.data

import com.google.gson.annotations.SerializedName

data class TeamMemberPhoto(
    @SerializedName("photo")
    var photo: String?
)