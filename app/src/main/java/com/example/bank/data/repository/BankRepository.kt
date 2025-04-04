package com.example.bank.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.bank.data.db.UserDao
import com.example.bank.data.db.UserEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BankRepository @Inject constructor(
    val userDao: UserDao,
    @ApplicationContext private val context: Context
) {
    var temporaryPinCode = ""

    suspend fun registerUser(email: String, password: String) {
        val user = UserEntity(email = email, password = password)
        userDao.registerUser(user)
    }

    suspend fun saveProfileInfo(firstName: String, lastName: String, uri: Uri?) {
        uri?.let {
            try {
                context.contentResolver.takePersistableUriPermission(
                    it, 
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (e: Exception) {
                Log.e("SecurityException", "Failed to take persistable URI permission", e)
            }
        }
        userDao.saveUserProfileInfo(firstName, lastName, uri?.toString() ?: "")
    }

    suspend fun hasUser(): Boolean {
        return userDao.hasUser()
    }

    suspend fun savePhoneNumber(phoneNumber: String) {
        userDao.savePhoneNumber(phoneNumber)
    }

    suspend fun saveSecurityAnswer(securityAnswer: String) {
        userDao.saveSecurityAnswer(securityAnswer)
    }

    fun saveTemporaryPinCode(pinCode: String) {
        temporaryPinCode = pinCode
    }

    suspend fun savePinCode(pinCode: String) {
        userDao.savePinCode(pinCode)
    }

    suspend fun getPinCode(): String {
        return userDao.getPinCode() ?: ""
    }

    suspend fun saveCardColor(color: Color) {
        userDao.saveCardColor(color.toString())
    }

    suspend fun getUserFullName(): String {
        val firstName = userDao.getUserFirstName() ?: ""
        val lastName = userDao.getUserLastName() ?: ""
        return "$firstName $lastName"
    }

    suspend fun getUserEmail(): String {
        return userDao.getUserEmail() ?: ""
    }

    suspend fun getUserEntity(): UserEntity {
        return userDao.getUserEntity() ?: UserEntity()
    }

    suspend fun getPhoneNumber(): String {
        return userDao.getUserPhoneNumber() ?: ""
    }

    suspend fun getSecurityQuestionAnswer(): String {
        return userDao.getUserSecurityQuestionAnswer() ?: ""
    }

    suspend fun getCardColor(): String {
        return userDao.getUserCardColor() ?: ""
    }

    suspend fun checkEmailAndPassword(email: String, password: String): Boolean {
        return userDao.checkEmailAndPassword(email, password)
    }

}