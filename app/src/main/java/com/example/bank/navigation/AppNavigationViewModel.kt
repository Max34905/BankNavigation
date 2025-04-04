package com.example.bank.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NavigationState {
    object Loading: NavigationState()
    data class Ready(val startDestination: Any): NavigationState()
}

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    private val repository: BankRepository
) : ViewModel() {
    private val _navigationState = MutableStateFlow<NavigationState>(NavigationState.Loading)
    val navigationState: StateFlow<NavigationState> = _navigationState.asStateFlow()

    init {
        checkDatabase()
    }

    private fun checkDatabase() {
        viewModelScope.launch {
            val hasUser = repository.hasUser()
            _navigationState.value = NavigationState.Ready(if (hasUser) Authorization else StartScreen)
        }
    }
}