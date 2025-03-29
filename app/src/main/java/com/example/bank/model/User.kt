package com.example.bank.model

import android.net.Uri
import androidx.compose.ui.graphics.Color

data class User(
    var email: String = "",
    var password: String = "",
    var uri: Uri? = null,
    var firstName: String = "",
    var lastName: String = "",
    var phoneNumber: String = "",
    var securityQuestion: String = "",
    var pinCode: String = "",
    var color: Color = Color.Unspecified
)
