package com.example.bank.ui.screens.pincode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmPinCodeScreenViewModel @Inject constructor(
    private val repository: BankRepository
) : ViewModel() {
    private val _isPinCodeRight = MutableStateFlow(false)
    val isPinCodeRight: StateFlow<Boolean> = _isPinCodeRight.asStateFlow()

    fun checkPinCode(pinCode: String) {
        viewModelScope.launch {
            if (pinCode == repository.temporaryPinCode) {
                _isPinCodeRight.value = true
            } else {
                _isPinCodeRight.value = false
            }
        }
    }

    fun savePinCode(pinCode: String) {
        viewModelScope.launch {
            repository.savePinCode(pinCode)
        }
    }
}