package com.example.cricketmatchdetails.model

import java.io.Serializable

data class Wicket(
    val dismissal: Dismissal,
    val overs: Double,
    val player: String,
    val score: Int
): Serializable