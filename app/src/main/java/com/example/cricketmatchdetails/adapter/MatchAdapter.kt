package com.example.cricketmatchdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketmatchdetails.databinding.ItemTeamBinding
import com.example.cricketmatchdetails.model.Bowler
import com.example.cricketmatchdetails.model.MatchDetailsX
import com.example.cricketmatchdetails.model.Player
import com.example.cricketmatchdetails.model.Team

class MatchAdapter(
    private val matchDetails: MatchDetailsX,
    private val onItemClick: (Team) -> Unit
) :
    RecyclerView.Adapter<MatchAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.teamName.text = team.name
            binding.totalRuns.text = "${calculateTotalRuns(team.players)}"
            binding.oversBowled.text = "(${calculateTotalOvers(team.bowlers)})"

            binding.cvItem.setOnClickListener {
                onItemClick(team)
            }
        }

        private fun calculateTotalRuns(players: List<Player>): Int {
            return players.sumOf { it.runs }
        }

        private fun calculateTotalOvers(bowlers: List<Bowler>): Int {
            return bowlers.sumOf { it.overs }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = matchDetails.teams[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = matchDetails.teams.size
}
