package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ajinkya.formula1.common.convertDate
import com.ajinkya.formula1.common.convertTime
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.data.entity.Race
import com.ajinkya.formula1.databinding.FragmentScheduleBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
import com.ajinkya.formula1.viewmodel.ScheduleViewModel
import com.ajinkya.formula1.viewmodel.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(
    FragmentScheduleBinding::inflate
) {
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private lateinit var scheduleAdapter: RecyclerAdapter<Race, RowRacesBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setLiveDataObservers()
    }

    private fun setRecyclerView() {
        scheduleAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { race, _ ->
            textRound.text = race.round
            textRace.text = "${race.raceName}\n${race.Circuit.Location.country}"
            val date = convertDate(race.date)
            val time = convertTime(race.time)
            textDateTime.text = "$date\n$time"
        }

        scheduleAdapter.setClickListeners = { raceList ->
            binding.textRace.setOnClickListener {
                toast(raceList[adapterPosition].date)
            }
        }
    }

    private fun setLiveDataObservers() {
        scheduleViewModel.scheduleList.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.cardSchedule.isVisible = true
                    response.data?.let {
                        val raceList = it.MRData.RaceTable.Races
                        scheduleAdapter.updateData(raceList)
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