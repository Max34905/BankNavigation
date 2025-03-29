package com.example.bank.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Upsert
    suspend fun registerUser(user: UserEntity)

    @Query("SELECT COUNT(*) > 0 FROM user WHERE id = 0 LIMIT 1")
    suspend fun hasUser(): Boolean

    @Query("SELECT email FROM user WHERE id = 0")
    suspend fun getUserEmail(): String

    @Query("SELECT password FROM user WHERE id = 0")
    suspend fun getUserPassword(): String

    @Query("UPDATE user SET firstName = :firstName, lastName = :lastName, uri = :uri WHERE id = 0")
    suspend fun saveUserProfileInfo(firstName: String, lastName: String, uri: String)

    @Query("UPDATE user SET phoneNumber = :phoneNumber WHERE id = 0")
    suspend fun savePhoneNumber(phoneNumber: String)

    @Query("UPDATE user SET securityAnswer = :securityAnswer WHERE id = 0")
    suspend fun saveSecurityAnswer(securityAnswer: String)

    @Query("UPDATE user SET pinCode = :pinCode WHERE id = 0")
    suspend fun savePinCode(pinCode: String)

    @Query("SELECT pinCode FROM user WHERE id = 0")
    suspend fun getPinCode(): String

    @Query("UPDATE user SET color = :color WHERE id = 0")
    suspend fun saveCardColor(color: String)

    @Query("SELECT firstName FROM user WHERE id = 0")
    suspend fun getUserFirstName(): String

    @Query("SELECT lastName FROM user WHERE id = 0")
    suspend fun getUserLastName(): String

    @Query("SELECT phoneNumber FROM user WHERE id = 0")
    suspend fun getUserPhoneNumber(): String

    @Query("SELECT securityAnswer FROM user WHERE id = 0")
    suspend fun getUserSecurityQuestionAnswer(): String

    @Query("SELECT uri FROM user WHERE id = 0")
    suspend fun getUserUri(): String

    @Query("SELECT color FROM user WHERE id = 0")
    suspend fun getUserCardColor(): String

    @Query("SELECT * FROM user WHERE id = 0")
    suspend fun getUserEntity(): UserEntity

    @Query("SELECT COUNT(*) > 0 FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun checkEmailAndPassword(email: String, password: String): Boolean
}