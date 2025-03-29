package com.example.bank.ui.screens.login

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
class LogInScreenViewModel @Inject constructor(
    private val repository: BankRepository
): ViewModel() {
    private val _isProfileInfoFilled = MutableStateFlow(false)
    val isProfileInfoFilled: StateFlow<Boolean> = _isProfileInfoFilled.asStateFlow()

    private val _isUserExists = MutableStateFlow(false)
    val isUserExists: StateFlow<Boolean> = _isUserExists.asStateFlow()

    private val _isPhoneNumberAdded = MutableStateFlow(false)
    val isPhoneNumberAdded: StateFlow<Boolean> = _isPhoneNumberAdded.asStateFlow()

    private val _isSecurityQuestionAnswerAdded = MutableStateFlow(false)
    val isSecurityQuestionAnswerAdded: StateFlow<Boolean> = _isSecurityQuestionAnswerAdded.asStateFlow()

    private val _isPinCodeAdded = MutableStateFlow(false)
    val isPinCodeAdded: StateFlow<Boolean> = _isPinCodeAdded.asStateFlow()

    private val _isCardColorAdded = MutableStateFlow(false)
    val isCardColorAdded: StateFlow<Boolean> = _isCardColorAdded.asStateFlow()

    init {
        checkProfileInfoExistence()
        checkPhoneNumberExistence()
        checkSecurityQuestionAnswerExistence()
        checkPinCodeExistence()
        checkCardColorExistence()
    }

    fun checkEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _isUserExists.value = repository.checkEmailAndPassword(email.trim(), password.trim())
        }
    }

    fun checkProfileInfoExistence() {
        viewModelScope.launch {
            val userEntity = repository.getUserEntity()
            _isProfileInfoFilled.value = userEntity.firstName.isNotEmpty() || userEntity.lastName.isNotEmpty();
        }
    }

    fun checkPhoneNumberExistence() {
        viewModelScope.launch {
            val phoneNumber = repository.getPhoneNumber()
            _isPhoneNumberAdded.value = phoneNumber.isNotEmpty();
        }
    }

    fun checkSecurityQuestionAnswerExistence() {
        viewModelScope.launch {
            val securityQuestionAnswer = repository.getSecurityQuestionAnswer()
            _isSecurityQuestionAnswerAdded.value = securityQuestionAnswer.isNotEmpty()
        }
    }

    fun checkPinCodeExistence() {
        viewModelScope.launch {
            val pinCode = repository.getPinCode()
            _isPinCodeAdded.value = pinCode.isNotEmpty()
        }
    }

    fun checkCardColorExistence() {
        viewModelScope.launch {
            val cardColor = repository.getCardColor()
            _isCardColorAdded.value = cardColor.isNotEmpty()
        }
    }
}