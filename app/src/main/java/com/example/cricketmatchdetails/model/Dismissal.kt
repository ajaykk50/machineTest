package com.example.cricketmatchdetails.model

import java.io.Serializable

data class Dismissal(
    val bowler: String?,
    val fielder: String?,
    val type: String?
): Serializable