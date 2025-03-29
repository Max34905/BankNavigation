package com.example.bank.ui.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bank.data.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileScreenViewModel @Inject constructor(
    private val repository: BankRepository
): ViewModel() {
    fun saveProfileInfo(firstName: String, lastName: String, uri: Uri?) {
        viewModelScope.launch {
            val capitalizedFirstName = firstName.trim().replaceFirstChar { it.uppercase() }
            val capitalizedLastName = lastName.trim().replaceFirstChar { it.uppercase() }
            repository.saveProfileInfo(capitalizedFirstName, capitalizedLastName, uri)
        }
    }
}