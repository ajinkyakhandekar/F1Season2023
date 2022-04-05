package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajinkya.formula1.R
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.databinding.FragmentDriverBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.domain.model.Driver
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
import com.ajinkya.formula1.ui.state.DriverStandingsState
import com.ajinkya.formula1.ui.viewmodel.DriverStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DriverFragment : BaseFragment<FragmentDriverBinding>(
    FragmentDriverBinding::inflate
) {

    private val driverStandingsViewModel: DriverStandingsViewModel by viewModels()
    private lateinit var standingsAdapter: RecyclerAdapter<Driver, RowRacesBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeTitle.apply {
            textTitleLeft.text = getString(R.string.position)
            textTitleCenter.text = "Driver/Constructor"
            textTitleRight.text = "Points"
        }

        setRecyclerView()
        //setObservers()
    }

    private fun setRecyclerView() {
        standingsAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { driver, _ ->
            val name = "${driver.driverName}\n${driver.constructorName}"

            binding.textRound.text = driver.position
            binding.textRace.text = name
            binding.textDateTime.text = driver.points
        }
    }

    /*private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            driverStandingsViewModel.uiState.collect { uiState ->
                when (uiState) {
                    is DriverStandingsState.Loading -> {
                        binding.progressBar.isVisible = true
                        standingsAdapter.updateData(uiState.drivers)
                    }
                    is DriverStandingsState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.cardSchedule.isVisible = true
                        standingsAdapter.updateData(uiState.drivers)
                    }
                    is DriverStandingsState.Error -> {
                        binding.progressBar.isVisible = false
                        toast(uiState.error)
                    }
                }
            }
        }
    }*/
}