package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajinkya.formula1.common.*
import com.ajinkya.formula1.databinding.FragmentScheduleBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.domain.model.Schedule
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
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
        setObservers()
    }

    private fun setRecyclerView() {
        scheduleAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { schedule, itemDetails ->
            val raceName = "${schedule.raceName}\n${schedule.country}"
            val dateTime = "${schedule.date}\n${schedule.time}"

            binding.textRound.text = schedule.round
            binding.textRace.text = raceName
            binding.textDateTime.text = dateTime
            binding.viewDivider.isGone = itemDetails.isLast
        }

        /*scheduleAdapter.setClickListeners = { raceList ->
            binding.textRace.setOnClickListener {
                toast(raceList[adapterPosition].date)
            }
        }*/
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            scheduleViewModel.uiState.collect { uiState ->

                uiState.jsonString.log("ui state")

                binding.progressBar.isVisible = uiState.isLoading
                binding.cardSchedule.isVisible = uiState.schedule.isNotEmpty()
                scheduleAdapter.updateData(uiState.schedule)

                uiState.errorMessage.ifNotEmpty {
                    toast(uiState.errorMessage)
                }
            }
        }
    }
}