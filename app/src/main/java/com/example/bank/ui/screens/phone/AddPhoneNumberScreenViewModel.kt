package com.example.bank.ui.screens.phone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPhoneNumberScreenViewModel @Inject constructor(
    private val repository: BankRepository
): ViewModel() {
    fun savePhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            repository.savePhoneNumber(phoneNumber.trim())
        }
    }
}