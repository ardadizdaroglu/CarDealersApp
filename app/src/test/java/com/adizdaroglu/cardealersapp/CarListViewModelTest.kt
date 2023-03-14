package com.adizdaroglu.cardealersapp

import com.adizdaroglu.cardealersapp.common.Resource
import com.adizdaroglu.cardealersapp.domain.model.Car
import com.adizdaroglu.cardealersapp.domain.usecase.GetCarsUseCase
import com.adizdaroglu.cardealersapp.presentation.car_list.CarListState
import com.adizdaroglu.cardealersapp.presentation.car_list.CarListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CarListViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule(this)

    @MockK
    private lateinit var getCarsUseCase: GetCarsUseCase

    private lateinit var viewModel: CarListViewModel

    @Before
    fun setUp() {
        viewModel = CarListViewModel(getCarsUseCase)
    }

    @Test
    fun `when fetchCars() is called and use case is loading object`() {
        testCoroutineRule.runBlockingTest {
            coEvery { getCarsUseCase.invokeCars() } returns flow { emit(Resource.Loading<List<Car>>()) }
            viewModel.fetchCars()
            val stateObject = viewModel.state.value
            Assert.assertNotNull(stateObject)
            Assert.assertEquals(
                CarListState(isLoading = true),
                stateObject
            )
            coVerify(exactly = 1) { getCarsUseCase.invokeCars() }
        }
    }

    @Test
    fun `when fetchCars() is called and use case returns object`() {
        testCoroutineRule.runBlockingTest {
            coEvery { getCarsUseCase.invokeCars() } returns flow {
                emit(
                    Resource.Success<List<Car>>(
                        emptyList()
                    )
                )
            }
            viewModel.fetchCars()
            val stateObject = viewModel.state.value
            Assert.assertNotNull(stateObject)
            Assert.assertEquals(
                CarListState(carList = emptyList()),
                stateObject
            )
            coVerify(exactly = 1) { getCarsUseCase.invokeCars() }
        }
    }

    @Test
    fun `when fetchCars() is called and use case returns error`() {
        testCoroutineRule.runBlockingTest {
            coEvery { getCarsUseCase.invokeCars() } returns flow { emit(Resource.Error<List<Car>>("error")) }
            viewModel.fetchCars()
            val stateObject = viewModel.state.value
            Assert.assertNotNull(stateObject)
            Assert.assertEquals(
                CarListState(error = "error"),
                stateObject
            )
            coVerify(exactly = 1) { getCarsUseCase.invokeCars() }
        }
    }
}
