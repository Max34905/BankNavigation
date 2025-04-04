package com.example.bank.utils

import android.util.Patterns
import kotlin.text.Regex

object InputValidator {
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
        return regex.matches(password)
    }

    fun areNamesValid(firstName: String, lastName: String): Boolean {
        val regex = Regex("^[a-zA-Z]+$")
        return regex.matches(firstName) && regex.matches(lastName)
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        val regex = Regex("^\\d{10,15}$")
        return regex.matches(phoneNumber)
    }
}