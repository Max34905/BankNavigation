package com.example.bank.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class BankDataBase : RoomDatabase() {
    abstract val userDao: UserDao
}