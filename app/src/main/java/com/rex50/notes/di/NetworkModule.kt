package com.rex50.notes.di

import com.google.gson.GsonBuilder
import com.rex50.notes.BuildConfig
import com.rex50.notes.data.network.NotesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor





@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNotesService(): NotesService {

        //For logging network calls
        val logging = HttpLoggingInterceptor()
        logging.setLevel(if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(httpClient.build())
            .build()
            .create(NotesService::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String{
        return "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOjAuMC4wLjA6ODA4MC9oZWxsbyIsImlzcyI6Imh0dHA6MC4wLjAuMDo4MDgwLyIsImV4cCI6MTYzNjM2MzA1NSwidXNlcklkIjoxLCJ1c2VybmFtZSI6InJleDUwIn0.pWpSZ1_hndnGfrfZ068riaJB2Rp-XhhA0P8bxvGg1LY"
    }

}