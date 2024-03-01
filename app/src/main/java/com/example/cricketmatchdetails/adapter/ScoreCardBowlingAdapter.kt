package com.example.cricketmatchdetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketmatchdetails.databinding.ScoreCardBowlingBinding
import com.example.cricketmatchdetails.model.Bowler
import com.example.cricketmatchdetails.model.Player

class ScoreCardBowlingAdapter(private val bowlerDetails: List<Bowler>) :
    RecyclerView.Adapter<ScoreCardBowlingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ScoreCardBowlingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Bowler, position: Int) {

            binding.bowlerName.text = player.name
            binding.runsConceded.text = player.runsConceded.toString()
            binding.oversBowled.text = player.overs.toString()
            binding.wicketsTaken.text = player.wickets.toString()

            val economy = calculateEconomy(player.runsConceded, player.overs)
            binding.economy.text = economy

            if (position == 0) {
                binding.llTitle.visibility = View.VISIBLE
            } else {
                binding.llTitle.visibility = View.GONE
            }
        }

        private fun calculateTotalRuns(players: List<Player>): Int {
            return players.sumOf { it.runs }
        }

        private fun calculateTotalOvers(bowlers: List<Bowler>): Int {
            return bowlers.sumOf { it.overs }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ScoreCardBowlingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bowler = bowlerDetails.get(position)
        holder.bind(bowler, position)
    }

    override fun getItemCount(): Int = bowlerDetails.size

    private fun calculateEconomy(runsConceded: Int, oversBowled: Int): String {
        // Ensure no division by zero
        return if (oversBowled != 0) {
            String.format("%.2f", runsConceded.toFloat() / oversBowled.toFloat())
        } else {
            "N/A" // Handle the case when no overs are bowled
        }
    }
}
