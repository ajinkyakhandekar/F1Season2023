package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.data.remote.dto.ConstructorStanding
import com.ajinkya.formula1.databinding.FragmentDriverBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
import com.ajinkya.formula1.ui.viewmodel.ConstructorStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConstructorFragment : BaseFragment<FragmentDriverBinding>(
    FragmentDriverBinding::inflate
) {

    private val standingsViewModel: ConstructorStandingsViewModel by viewModels()
    private lateinit var standingsAdapter: RecyclerAdapter<ConstructorStanding, RowRacesBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeTitle.apply {
            textTitleLeft.text = "Position"
            textTitleCenter.text = "Constructor"
            textTitleRight.text = "Points"
        }

        setRecyclerView()
       // setLiveDataObservers()
    }

    private fun setRecyclerView() {
        standingsAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { constructorStanding, _ ->
            binding.textRound.text = constructorStanding.position
            binding.textRace.text = constructorStanding.Constructor.name
            binding.textDateTime.text = constructorStanding.points
        }
    }

    /*private fun setLiveDataObservers() {
        standingsViewModel.constructorStandingsList.observe(this) { response ->
            when (response.status) {
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.cardSchedule.isVisible = true
                    response.data?.let {
                        val constructorList = it.MRData.StandingsTable.StandingsLists[0].ConstructorStandings
                        standingsAdapter.updateData(constructorList)
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    toast(response.error)
                }
            }
        }
    }*/
}