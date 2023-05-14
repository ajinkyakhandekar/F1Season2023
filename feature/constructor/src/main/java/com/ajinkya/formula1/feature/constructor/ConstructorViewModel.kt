package com.ajinkya.formula1.feature.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.core.data.repository.ConstructorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConstructorViewModel @Inject constructor(
    private val constructorRepository: ConstructorRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ConstructorState())
    val state = _state.asStateFlow()

     fun getConstructorStandings() {
        viewModelScope.launch {
            constructorRepository.getConstructors()
                .collect { constructorList ->
                    _state.update {
                        it.copy(constructors = constructorList)
                    }
                }
        }
    }
}