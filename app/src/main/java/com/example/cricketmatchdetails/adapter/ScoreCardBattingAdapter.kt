package com.example.cricketmatchdetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketmatchdetails.DismissalType
import com.example.cricketmatchdetails.databinding.ScoreCardBattingBinding
import com.example.cricketmatchdetails.model.Player

class ScoreCardBattingAdapter(private val playerDetails: List<Player>) :
    RecyclerView.Adapter<ScoreCardBattingAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(val binding: ScoreCardBattingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player, position: Int) {
            binding.playerName.text = player.name
            binding.runs.text = player.runs.toString()
            binding.ballsFaced.text = player.balls.toString()
            binding.sixes.text = player.sixes.toString()
            binding.fours.text = player.fours.toString()

            if (position == 0) {
                binding.llTitla.visibility = View.VISIBLE
            } else {
                binding.llTitla.visibility = View.GONE
            }

            if (position == playerDetails.size - 1) {
                binding.rlTotal.visibility = View.VISIBLE
                var totalteamRun = playerDetails.let {
                    calculateTotalRuns(
                        it
                    )
                }

                binding.tvTotal.text = totalteamRun.toString()
            } else {
                binding.rlTotal.visibility = View.GONE
            }


            val strikeRateValue = calculateStrikeRate(player.runs, player.balls)
            binding.strikeRate.text = strikeRateValue

            if (player.dismissal.type != "") {
                binding.playerDismissal.visibility = View.VISIBLE
                val dismissalType = player.dismissal.type?.let { DismissalType.fromDisplayName(it) }
                val dismissalDisplayName = dismissalType?.displayName ?: ""
                val fielder = player.dismissal.fielder ?: ""
                val bowler = player.dismissal.bowler
                if (fielder != "")
                    binding.playerDismissal.text =
                        "$dismissalDisplayName c $fielder b $bowler".trim()
                else
                    binding.playerDismissal.text = "$dismissalDisplayName b $bowler".trim()

            } else {
                binding.playerDismissal.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ScoreCardBattingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val batting = playerDetails.get(position)
        holder.bind(batting, position)
    }

    override fun getItemCount(): Int = playerDetails.size

    private fun calculateStrikeRate(runs: Int, balls: Int): String {
        return if (balls > 0) {
            String.format("%.2f", (runs.toFloat() / balls) * 100)
        } else {
            "0.00"
        }
    }

    private fun calculateTotalRuns(players: List<Player>): Int {
        return players.sumOf { it.runs }
    }
}
