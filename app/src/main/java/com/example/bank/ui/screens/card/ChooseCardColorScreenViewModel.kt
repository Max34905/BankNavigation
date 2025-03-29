package com.example.bank.ui.screens.card

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseCardColorScreenViewModel @Inject constructor(
    private val repository: BankRepository
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    fun saveCardColor(color: Color) {
        viewModelScope.launch {
            repository.saveCardColor(color)
        }
    }

    fun getUserFullName() {
        viewModelScope.launch {
            _name.value = repository.getUserFullName()
        }
    }
}