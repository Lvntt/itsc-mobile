package com.example.itscproject.data

import com.example.itscproject.R
import com.example.itscproject.model.TeamMember

object DataSource {

    val teamMembers: List<TeamMember> = listOf(
        TeamMember(
            R.drawable.faye,
            "Матвей Серегин",
            "Android-разработчик",
            "HITs, 1 курс"
        ),
        TeamMember(
            R.drawable.frankie,
            "Даниил Хахулин",
            "Frontend-разработчик",
            "HITs, 1 курс"
        ),
        TeamMember(
            R.drawable.leroy,
            "Гордей Довыденко",
            "Backend-разработчик",
            "HITs, 1 курс"
        ),
        TeamMember(
            R.drawable.nox,
            "Руслан Гафаров",
            "Android-разработчик",
            "HITs, 1 курс"
        )
    )
}