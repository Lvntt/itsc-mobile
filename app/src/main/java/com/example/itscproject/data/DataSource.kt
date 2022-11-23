package com.example.itscproject.data

import com.example.itscproject.R
import com.example.itscproject.model.TeamMember

object DataSource {

    val teamMembers: List<TeamMember> = listOf(
        TeamMember(
            0,
            R.drawable.faye,
            "Матвей Серегин",
            "Android-разработчик",
            "HITs, 1 курс",
            "Stub info(Матвей Серегин)"
        ),
        TeamMember(
            1,
            R.drawable.frankie,
            "Даниил Хахулин",
            "Frontend-разработчик",
            "HITs, 1 курс",
            "Stub info(Даниил Хахулин)"
        ),
        TeamMember(
            2,
            R.drawable.leroy,
            "Гордей Довыденко",
            "Backend-разработчик",
            "HITs, 1 курс",
            "Stub info(Гордей Довыденко)"
        ),
        TeamMember(
            3,
            R.drawable.nox,
            "Руслан Гафаров",
            "Android-разработчик",
            "HITs, 1 курс",
            "Stub info(Руслан Гафаров)"
        )
    )
}