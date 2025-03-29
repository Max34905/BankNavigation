package com.example.bank.model

import androidx.annotation.DrawableRes

data class BankCard(
    @DrawableRes val image: Int,
    val name: String,
    val number: String,
    val paymentSystem: String,
    val balance: String
)
