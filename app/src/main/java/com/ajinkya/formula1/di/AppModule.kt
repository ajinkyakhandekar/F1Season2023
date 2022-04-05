package com.ajinkya.formula1.di

import android.content.Context
import androidx.room.Room
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.database.F1Database
import com.ajinkya.formula1.data.local.data_source.LocalDataSource
import com.ajinkya.formula1.data.remote.data_source.RemoteDataSource
import com.ajinkya.formula1.data.remote.service.ApiService
import com.ajinkya.formula1.data.repository.ConstructorStandingsRepository
import com.ajinkya.formula1.data.repository.DriverStandingsRepository
import com.ajinkya.formula1.data.repository.ScheduleRepository
import com.ajinkya.formula1.domain.use_case.GetConstructorStandingsUseCase
import com.ajinkya.formula1.domain.use_case.GetDriverStandingsUseCase
import com.ajinkya.formula1.domain.use_case.GetScheduleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return retrofit.client(client).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        F1Database::class.java,
        Constant.F1_DATABASE
    ).build()


    @Singleton
    @Provides
    fun provideDao(db: F1Database) : F1Dao = db.f1Dao()

    @Provides
    @Singleton
    fun provideLocalDataSource(f1Dao: F1Dao): LocalDataSource {
        return LocalDataSource(f1Dao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(
        localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource
    ): ScheduleRepository {
        return ScheduleRepository(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideDriverStandingsRepository(
        localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource
    ): DriverStandingsRepository {
        return DriverStandingsRepository(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideConstructorStandingsRepository(
        localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource
    ): ConstructorStandingsRepository {
        return ConstructorStandingsRepository(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideScheduleUseCase(scheduleRepository: ScheduleRepository): GetScheduleUseCase {
        return GetScheduleUseCase(scheduleRepository)
    }

    @Provides
    @Singleton
    fun provideConstructorStandingsUseCase(constructorStandingsRepository: ConstructorStandingsRepository): GetConstructorStandingsUseCase {
        return GetConstructorStandingsUseCase(constructorStandingsRepository)
    }

    @Provides
    @Singleton
    fun provideDriverStandingsUseCase(driverStandingsRepository: DriverStandingsRepository): GetDriverStandingsUseCase {
        return GetDriverStandingsUseCase(driverStandingsRepository)
    }
}