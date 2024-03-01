package com.example.cricketmatchdetails.model

import java.io.Serializable

data class Team(
    val bowlers: List<Bowler>,
    val name: String,
    val players: List<Player>
): Serializable