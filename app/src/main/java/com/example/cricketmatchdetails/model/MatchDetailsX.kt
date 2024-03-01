package com.example.cricketmatchdetails.model

import java.io.Serializable

data class MatchDetailsX(
    val fallOfWickets: List<FallOfWicket>,
    val format: String,
    val teams: List<Team>,
    val toss: String,
    val toss_decision: String
): Serializable