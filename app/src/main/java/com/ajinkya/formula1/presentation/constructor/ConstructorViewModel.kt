package com.ajinkya.formula1.presentation.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.data.repository.ConstructorRepository
import com.ajinkya.formula1.domain.use_case.GetConstructorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HiltViewModel -
 * MutableStateFlow -
 */
@HiltViewModel
class ConstructorViewModel @Inject constructor(
    private val constructorRepository: ConstructorRepository,
    private val getConstructorsUseCase: GetConstructorsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ConstructorState())
    val state = _state.asStateFlow()

    init {
        getConstructorStandings()
        loadConstructorStandings()
    }

    private fun getConstructorStandings() {
        viewModelScope.launch {
            getConstructorsUseCase()
                .flowOn(Dispatchers.IO)
                .collect { constructorList ->
                    _state.update {
                        it.copy(constructors = constructorList)
                    }
                }
        }
    }

    private fun loadConstructorStandings() {
        viewModelScope.launch {
            constructorRepository.loadConstructors()
                .catch { }
                .collect()
        }
    }
}