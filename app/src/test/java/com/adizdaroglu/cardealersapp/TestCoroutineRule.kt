package com.adizdaroglu.cardealersapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class TestCoroutineRule(
    private val test: Any,
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : InstantTaskExecutorRule() {

    private val testCoroutineScope = TestCoroutineScope(dispatcher)

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(test, relaxUnitFun = true)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        testCoroutineScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest { block() }
}