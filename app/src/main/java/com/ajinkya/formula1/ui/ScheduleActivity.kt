package com.ajinkya.formula1.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.databinding.ActivityScheduleBinding
import com.ajinkya.formula1.viewmodel.ScheduleViewModel
import com.ajinkya.formula1.viewmodel.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleBinding
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        setLiveDataObservers()
    }

    private fun setRecyclerView() {
        scheduleAdapter = ScheduleAdapter()
        binding.recyclerSchedule.adapter = scheduleAdapter
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