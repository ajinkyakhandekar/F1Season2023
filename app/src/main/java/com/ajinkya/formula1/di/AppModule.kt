package com.ajinkya.formula1.di

import android.content.Context
import androidx.room.Room
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.database.F1Database
import com.ajinkya.formula1.data.local.repository.LocalRepository
import com.ajinkya.formula1.data.local.repository.LocalRepositoryImpl
import com.ajinkya.formula1.data.remote.repository.RemoteRepository
import com.ajinkya.formula1.data.remote.repository.RemoteRepositoryImpl
import com.ajinkya.formula1.data.remote.service.ApiService
import com.ajinkya.formula1.data.repository.F1Repository
import com.ajinkya.formula1.data.repository.F1RepositoryImpl
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
    fun provideDao(db: F1Database) = db.f1Dao()


    @Provides
    @Singleton
    fun provideLocalRepository(
        f1Dao: F1Dao
    ): LocalRepository {
        return LocalRepositoryImpl(f1Dao)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(
        apiService: ApiService
    ): RemoteRepository {
        return RemoteRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideF1Repository(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): F1Repository {
        return F1RepositoryImpl(localRepository, remoteRepository)
    }

    @Provides
    @Singleton
    fun provideGetScheduleUseCase(repository: F1Repository): GetScheduleUseCase {
        return GetScheduleUseCase(repository)
    }
}