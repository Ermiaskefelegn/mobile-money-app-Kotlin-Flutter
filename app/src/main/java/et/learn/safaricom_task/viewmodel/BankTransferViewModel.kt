package com.mobilemoney.viewmodel

import BankTransferUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankTransferViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(BankTransferUiState())
    val uiState: StateFlow<BankTransferUiState> = _uiState.asStateFlow()

    fun updateSelectedBank(bank: String) {
        _uiState.value = _uiState.value.copy(selectedBank = bank)
        validateForm()
    }

    fun updateAccountNumber(accountNumber: String) {
        _uiState.value = _uiState.value.copy(
            accountNumber = accountNumber,
            accountNumberError = null
        )
        validateForm()
    }

    fun updateAmount(amount: String) {
        _uiState.value = _uiState.value.copy(
            amount = amount,
            amountError = null
        )
        validateForm()
    }

    fun updateDescription(description: String) {
        if (description.length <= 100) {
            _uiState.value = _uiState.value.copy(description = description)
        }
    }

    fun validateForm(): Boolean {
        val currentState = _uiState.value
        var isValid = true
        var accountNumberError: String? = null
        var amountError: String? = null

        // Validate account number
        if (currentState.accountNumber.isEmpty()) {
            accountNumberError = "Account number is required"
            isValid = false
        } else if (currentState.accountNumber.length < 10) {
            accountNumberError = "Account number must be at least 10 digits"
            isValid = false
        }

        // Validate amount
        val amountValue = currentState.amount.toDoubleOrNull()
        if (currentState.amount.isEmpty()) {
            amountError = "Amount is required"
            isValid = false
        } else if (amountValue == null || amountValue < 25) {
            amountError = "Amount must be at least 25 Birr"
            isValid = false
        }

        // Validate bank selection
        if (currentState.selectedBank == "Select bank") {
            isValid = false
        }

        _uiState.value = currentState.copy(
            accountNumberError = accountNumberError,
            amountError = amountError,
            isFormValid = isValid
        )

        return isValid
    }
}
