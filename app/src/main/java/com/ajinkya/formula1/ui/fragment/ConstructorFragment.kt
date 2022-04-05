package com.ajinkya.formula1.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajinkya.formula1.R
import com.ajinkya.formula1.common.ifNotEmpty
import com.ajinkya.formula1.common.toast
import com.ajinkya.formula1.databinding.FragmentDriverBinding
import com.ajinkya.formula1.databinding.RowRacesBinding
import com.ajinkya.formula1.domain.model.Constructor
import com.ajinkya.formula1.ui.adapter.RecyclerAdapter
import com.ajinkya.formula1.ui.adapter.withAdapter
import com.ajinkya.formula1.ui.viewmodel.ConstructorStandingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ConstructorFragment : BaseFragment<FragmentDriverBinding>(
    FragmentDriverBinding::inflate
) {

    private val constructorStandingsViewModel: ConstructorStandingsViewModel by viewModels()
    private lateinit var standingsAdapter: RecyclerAdapter<Constructor, RowRacesBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeTitle.apply {
            textTitleLeft.text = getString(R.string.position)
            textTitleCenter.text = getString(R.string.constructor_title)
            textTitleRight.text = getString(R.string.points)
        }

        setRecyclerView()
        setObservers()
    }

    private fun setRecyclerView() {
        standingsAdapter = binding.recyclerSchedule.withAdapter(
            RowRacesBinding::inflate
        ) { constructor, _ ->
            binding.textRound.text = constructor.position
            binding.textRace.text = constructor.constructorName
            binding.textDateTime.text = constructor.points
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            constructorStandingsViewModel.uiState.collect { uiState ->

                binding.progressBar.isVisible = uiState.isLoading
                binding.cardSchedule.isVisible = uiState.constructorList.isNotEmpty()
                standingsAdapter.updateData(uiState.constructorList)

                uiState.errorMessage.ifNotEmpty {
                    toast(uiState.errorMessage)
                }
            }
        }
    }
}