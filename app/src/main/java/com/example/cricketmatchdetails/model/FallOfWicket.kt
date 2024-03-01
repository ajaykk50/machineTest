package com.example.cricketmatchdetails.model

import java.io.Serializable

data class FallOfWicket(
    val team: String,
    val wickets: List<Wicket>
): Serializable