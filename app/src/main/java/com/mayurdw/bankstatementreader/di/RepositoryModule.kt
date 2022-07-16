package com.mayurdw.bankstatementreader.di

import com.mayurdw.bankstatementreader.data.Repository
import com.mayurdw.bankstatementreader.data.mapper.RoomMapper
import com.mayurdw.bankstatementreader.data.room.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        transactionDao: TransactionDao,
        roomMapper: RoomMapper
    ) : Repository = Repository(
        transactionDao = transactionDao,
        roomMapper = roomMapper
    )
}