package com.example.cricketmatchdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cricketmatchdetails.databinding.FragmentMatchSummeryBinding
import com.example.cricketmatchdetails.model.Bowler
import com.example.cricketmatchdetails.model.Player
import com.example.cricketmatchdetails.model.matchDetails
import com.google.gson.Gson


class MatchSummeryFragment : Fragment() {

    private lateinit var _binding: FragmentMatchSummeryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchSummeryBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonFileName = "status.json"
        val jsonObject = readJsonFromAssets(requireActivity(), jsonFileName)
        val matchDetails: matchDetails? =
            convertJsonToDataClass(jsonObject.toString(), matchDetails::class.java)

        _binding.tvMatchType.text = matchDetails?.matchDetails?.format
        _binding.tvFirstteam.text = matchDetails?.matchDetails?.teams?.get(0)?.name
        _binding.tvSecondteam.text = matchDetails?.matchDetails?.teams?.get(1)?.name

        var totalFirstTeamRun = matchDetails?.matchDetails?.teams?.get(0)?.players?.let {
            calculateTotalRuns(
                it
            )
        }

        var totalFirstTeamOver = matchDetails?.matchDetails?.teams?.get(0)?.bowlers?.let {
            calculateTotalOvers(
                it
            )
        }

        var totalSecondTeamRun = matchDetails?.matchDetails?.teams?.get(1)?.players?.let {
            calculateTotalRuns(
                it
            )
        }

        var totalSecondTeamOver = matchDetails?.matchDetails?.teams?.get(1)?.bowlers?.let {
            calculateTotalOvers(
                it
            )
        }

        var firstTeamRun = "$totalFirstTeamRun($totalFirstTeamOver)"
        var secondTeamRun = "$totalSecondTeamRun($totalSecondTeamOver)"

        _binding.tvFirstteamRun.setText(firstTeamRun)
        _binding.tvSecondteamRun.setText(secondTeamRun)
        _binding.tvWinningToss.setText(matchDetails?.matchDetails?.toss + " Selected " + matchDetails?.matchDetails?.toss_decision)

        var difference = totalFirstTeamRun!! - totalSecondTeamRun!!
        if(difference<0){
            _binding.tvWinningMatch.setText(matchDetails?.matchDetails?.teams?.get(1)?.name +" won the match")
        }else if(difference>0){
            _binding.tvWinningMatch.setText(matchDetails?.matchDetails?.teams?.get(0)?.name +" won the match")
        }else{
            _binding.tvWinningMatch.setText("Match Tied")
        }

        _binding.cvContainer.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("matchdetails", matchDetails)
            }
            findNavController().navigate(
                R.id.action_matchSummeryFragment_to_scorecardFragment,
                bundle
            )
        }

    }

    private fun calculateTotalRuns(players: List<Player>): Int {
        return players.sumOf { it.runs }
    }

    private fun calculateTotalOvers(bowlers: List<Bowler>): Int {
        return bowlers.sumOf { it.overs }
    }


    private fun <T> convertJsonToDataClass(json: String, clazz: Class<T>): T? {
        return try {
            val gson = Gson()
            gson.fromJson(json, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}