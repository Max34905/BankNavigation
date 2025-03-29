package com.example.bank.ui.screens.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecurityScreenViewModel @Inject constructor(
    private val repository: BankRepository
): ViewModel() {
    fun saveSecurityQuestion(securityQuestion: String) {
        viewModelScope.launch {
            repository.saveSecurityAnswer(securityQuestion)
        }
    }
}