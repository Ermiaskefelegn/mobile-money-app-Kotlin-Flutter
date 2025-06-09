package com.mobilemoney.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilemoney.data.TransferRepository
import com.mobilemoney.model.RecentTransfersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentTransfersViewModel @Inject constructor(
    private val transferRepository: TransferRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecentTransfersUiState())
    val uiState: StateFlow<RecentTransfersUiState> = _uiState.asStateFlow()

    init {
        loadTransfers()
    }

    private fun loadTransfers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val transfers = transferRepository.getAllTransfers()
                _uiState.value = _uiState.value.copy(
                    transfers = transfers,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun refreshTransfers() {
        loadTransfers()
    }
}
