package com.example.cricketmatchdetails.model

import java.io.Serializable

data class Bowler(
    val name: String,
    val overs: Int,
    val runsConceded: Int,
    val wickets: Int
): Serializable