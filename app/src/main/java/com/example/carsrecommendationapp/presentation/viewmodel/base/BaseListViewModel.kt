package com.example.carsrecommendationapp.presentation.viewmodel.base

import kotlinx.coroutines.CancellationException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseListViewModel<T>(
    protected val carRepository: CarRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<T>>(emptyList())
    val items: StateFlow<List<T>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()


    protected abstract suspend fun loadData(): List<T>

    fun loadItems() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null
                _items.value = loadData()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value = ""

            } finally {
                _isLoading.value = false
            }
        }
    }
}