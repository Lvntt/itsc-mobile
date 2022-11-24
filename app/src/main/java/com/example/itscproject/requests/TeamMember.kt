package com.example.itscproject.requests

import com.google.gson.annotations.SerializedName

data class TeamMember(
    @SerializedName("id")
    var id: String?,
    @SerializedName("author_id")
    var authorId: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("surname")
    var surname: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("education")
    var education: String?,
    @SerializedName("additional")
    var additional: String?,
    @SerializedName("photo")
    var photo: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("updated_at")
    var updatedAt: String?
)

