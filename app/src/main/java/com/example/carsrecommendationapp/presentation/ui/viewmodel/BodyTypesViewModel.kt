package com.example.carsrecommendationapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsrecommendationapp.data.network.RetrofitInstance
import com.example.carsrecommendationapp.domain.BodyType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BodyTypesViewModel : ViewModel() {

    private val _bodyTypes = MutableStateFlow<List<BodyType>>(emptyList())
    val bodyTypes: StateFlow<List<BodyType>> = _bodyTypes.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadBodyTypes()
    }

    fun loadBodyTypes() {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                _bodyTypes.value = RetrofitInstance.api.getBodyTypes()
            } catch (e: Exception) {
                _errorMessage.value = e.message
                e.printStackTrace()
            }
        }
    }
}