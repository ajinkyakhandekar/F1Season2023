package com.ajinkya.formula1.feature.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.core.data.repository.ConstructorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

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