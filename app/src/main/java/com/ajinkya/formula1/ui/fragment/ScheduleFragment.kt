package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajinkya.formula1.common.convertDate
import com.ajinkya.formula1.common.convertTime
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.data.local.entity.ScheduleEntity
import com.ajinkya.formula1.databinding.FragmentScheduleBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.domain.model.Schedule
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
import com.ajinkya.formula1.ui.state.ScheduleState
import com.ajinkya.formula1.ui.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(
    FragmentScheduleBinding::inflate
) {
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private lateinit var scheduleAdapter: RecyclerAdapter<Schedule, RowRacesBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setLiveDataObservers()
    }

    private fun setRecyclerView() {
        scheduleAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { schedule, _ ->
            textRound.text = schedule.round
            textRace.text = "${schedule.raceName}\n${schedule.country}"
            val date = convertDate(schedule.date)
            val time = convertTime(schedule.time)
            textDateTime.text = "$date\n$time"
        }

        /*scheduleAdapter.setClickListeners = { raceList ->
            binding.textRace.setOnClickListener {
                toast(raceList[adapterPosition].date)
            }
        }*/
    }

    private fun setLiveDataObservers() {
        lifecycleScope.launchWhenStarted {
            scheduleViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is ScheduleState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is ScheduleState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.cardSchedule.isVisible = true
                        scheduleAdapter.updateData(uiState.schedule)
                    }
                    is ScheduleState.Error -> {
                        binding.progressBar.isVisible = false
                        toast(uiState.error)
                    }
                }
            }
        }
    }
}