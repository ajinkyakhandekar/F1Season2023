package com.ajinkya.formula1.data.di

import android.content.Context
import androidx.room.Room
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.database.F1Database
import com.ajinkya.formula1.data.remote.service.ApiService
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

/**
 * @Module - Declare that this class contains initialisations for dependency injection
 * @InstallIn -
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * @Singleton - Create a Single Instance
     * @Provides - Create a provider method binding.
     * The component implementation will pass dependencies to the method as parameters
     *
     * Room.databaseBuilder - Extension function to create a RoomDatabase.Builder for a persistent database.
     */
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        F1Database::class.java,
        Constant.F1_DATABASE
    ).build()


    @Singleton
    @Provides
    fun provideDao(
        db: F1Database
    ): F1Dao {
        return db.f1Dao()
    }

    // ----------------- Remote Data Source -------------------- //
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
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
