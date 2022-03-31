package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.data.entity.DriverStanding
import com.ajinkya.formula1.databinding.FragmentDriverBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
import com.ajinkya.formula1.viewmodel.StandingsViewModel
import com.ajinkya.formula1.viewmodel.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DriverFragment : BaseFragment<FragmentDriverBinding>(
    FragmentDriverBinding::inflate
) {

    private val standingsViewModel: StandingsViewModel by viewModels()
    private lateinit var standingsAdapter: RecyclerAdapter<DriverStanding, RowRacesBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeTitle.apply {
            textTitleLeft.text = "Position"
            textTitleCenter.text = "Driver/Constructor"
            textTitleRight.text = "Points"
        }

        setRecyclerView()
        setLiveDataObservers()
    }

    private fun setRecyclerView() {
        standingsAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { driverStanding, _ ->
            textRound.text = driverStanding.position
            textRace.text =
                "${driverStanding.Driver.givenName} ${driverStanding.Driver.familyName}\n${driverStanding.Constructors[0].name}"
            textDateTime.text = driverStanding.points
        }
    }

    private fun setLiveDataObservers() {
        standingsViewModel.driverStandingsList.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.cardSchedule.isVisible = true
                    response.data?.let {
                        val driverList = it.MRData.StandingsTable.StandingsLists[0].DriverStandings
                        standingsAdapter.updateData(driverList)
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    toast(response.error)
                }
            }
        }
    }
}