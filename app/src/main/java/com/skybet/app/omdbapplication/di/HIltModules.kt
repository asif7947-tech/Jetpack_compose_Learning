package com.skybet.app.omdbapplication.di

import android.util.Log
import com.skybet.app.omdbapplication.utils.Constants.TIME_OUT
import com.skybet.app.omdbapplication.utils.Constants.getBaseUrl

import com.workerx.mycomposeapp.datalayer.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HIltModules {

    @Singleton
    @Provides
    fun provideloggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
//                    .addHeader("Authorization", "Bearer ${UserManager.getAuthToken()}")
                    /*.addHeader(
                        "cookie",
                        //SharedPreferenceManager.getInstance().read(PreferenceUtils.COOKIE, "")
                    )*/
                    .build()

                Log.e("TAG", "provideOkHTTPClient: request "+request.toString() )
                chain.proceed(request)
            }).build()
    }


    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): ApiService {
        return Retrofit.Builder().baseUrl(getBaseUrl()).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
            .create(ApiService::class.java)
    }
}