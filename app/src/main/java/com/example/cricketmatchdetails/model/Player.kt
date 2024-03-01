package com.example.cricketmatchdetails.model

import java.io.Serializable

data class Player(
    val balls: Int,
    val dismissal: Dismissal,
    val fours: Int,
    val name: String,
    val runs: Int,
    val sixes: Int
): Serializable