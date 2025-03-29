package com.example.bank.ui.screens.pincode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetPinCodeScreenViewModel @Inject constructor(
    private val repository: BankRepository
): ViewModel() {
    fun saveTemporaryPinCode(pinCode: String) {
        viewModelScope.launch {
            repository.saveTemporaryPinCode(pinCode)
        }
    }
}