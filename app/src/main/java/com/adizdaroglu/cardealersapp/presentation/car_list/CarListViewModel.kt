package com.adizdaroglu.cardealersapp.presentation.car_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adizdaroglu.cardealersapp.common.Resource
import com.adizdaroglu.cardealersapp.domain.usecase.GetCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CarListState())
    val state: State<CarListState> = _state

    fun fetchCars() {
        getCarsUseCase.invokeCars().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = CarListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CarListState(carList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CarListState(error = result.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}