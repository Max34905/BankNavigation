package com.example.bank.di

import android.content.Context
import androidx.room.Room
import com.example.bank.data.db.BankDataBase
import com.example.bank.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BankModule {
    @Provides
    @Singleton
    fun provideBankDataBase(
        @ApplicationContext context: Context
    ): BankDataBase {
        return Room.databaseBuilder(
            context,
            BankDataBase::class.java,
            "bank.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        db: BankDataBase
    ): UserDao {
        return db.userDao
    }
}