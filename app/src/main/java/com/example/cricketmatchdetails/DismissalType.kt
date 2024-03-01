package com.example.cricketmatchdetails

enum class DismissalType(val displayName: String) {
    CAUGHT("c"),
    RUN_OUT("run out"),
    LBW("lbw"),
    BOWLED("b"),
    STUMPED("stumped"),
    HIT_WICKET("hit wicket");

    companion object {
        fun fromDisplayName(displayName: String): DismissalType? {
            return entries.find { it.displayName == displayName }
        }
    }
}