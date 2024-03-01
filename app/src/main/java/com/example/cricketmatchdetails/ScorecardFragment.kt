package com.example.cricketmatchdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketmatchdetails.adapter.ScoreCardBattingAdapter
import com.example.cricketmatchdetails.adapter.ScoreCardBowlingAdapter
import com.example.cricketmatchdetails.databinding.FragmentMatchSummeryBinding
import com.example.cricketmatchdetails.databinding.FragmentScorecardBinding
import com.example.cricketmatchdetails.model.MatchDetailsX
import com.example.cricketmatchdetails.model.Team
import com.example.cricketmatchdetails.model.matchDetails
import com.google.android.material.tabs.TabLayout


class ScorecardFragment : Fragment() {

    private var team: matchDetails? = null
    private lateinit var _binding: FragmentScorecardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScorecardBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedBundle = arguments
        if (receivedBundle != null && receivedBundle.containsKey("matchdetails")) {
            team = receivedBundle.getSerializable("matchdetails") as? matchDetails
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.tbLayout.getTabAt(0)?.text = team?.matchDetails?.teams?.get(0)?.name
        _binding.tbLayout.getTabAt(1)?.text = team?.matchDetails?.teams?.get(1)?.name

        setAdapter(0)

        _binding.tbLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    setAdapter(0)
                } else if (tab?.position == 1) {
                    setAdapter(1)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    setAdapter(0)
                } else if (tab?.position == 1) {
                    setAdapter(1)
                }
            }

        })
    }

    private fun setAdapter(pos: Int) {
        var battingAdapter =
            team?.matchDetails?.teams?.get(pos)?.players?.let { ScoreCardBattingAdapter(it) }
        _binding.rvBattingTeam.layoutManager = LinearLayoutManager(requireActivity())
        _binding.rvBattingTeam.adapter = battingAdapter

        var bowlingAdapter =
            team?.matchDetails?.teams?.get(pos)?.bowlers?.let { ScoreCardBowlingAdapter(it) }
        _binding.rvBowlingTeam.layoutManager = LinearLayoutManager(requireActivity())
        _binding.rvBowlingTeam.adapter = bowlingAdapter
    }
}