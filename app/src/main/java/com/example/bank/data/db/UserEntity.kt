package com.example.bank.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (
    val id: Int = 0,
    @PrimaryKey
    val email: String = "",
    val password: String = "",
    val uri: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val securityAnswer: String = "",
    val pinCode: String = "",
    val color: String = ""
)