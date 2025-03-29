package com.example.bank.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repository: BankRepository
) : ViewModel() {
    
    // State for email existence check
    private val _emailInUseState = MutableStateFlow<Boolean?>(null)
    val emailInUseState: StateFlow<Boolean?> = _emailInUseState
    
    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(email.trim(), password.trim())
        }
    }

    fun checkIfEmailExists(email: String) {
        viewModelScope.launch {
            val exists = email == repository.getUserEmail()
            _emailInUseState.value = exists
        }
    }
}