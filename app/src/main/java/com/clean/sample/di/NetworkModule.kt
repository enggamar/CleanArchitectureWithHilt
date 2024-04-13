package com.clean.sample.di

import android.content.Context
import com.clean.sample.BuildConfig
import com.clean.sample.MyApplication
import com.clean.sample.data.remote.NetworkConstants
import com.clean.sample.data.remote.services.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Network module
 * THis function is used to setup Network layer using retrofit
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * This function is used to Provide application context
     *
     * @param applicationContext - This is the Context of MyApplication Class
     * @return - This function will return MyApplication instance
     */
//    @Singleton
//    @Provides
//    fun provideApplicationContext(application: MyApplication): Context {
//        return application.applicationContext
//    }

    /**
     * This function is used to Provide retrofit instance
     *
     * @param client
     * @return - This function will return retrofit instance
     */
    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
    }

    /**
     * This function is used to Provide okhttp client
     *
     * @param headerInterceptor - Interceptor which needs to add in interceptor
     * @param cache
     * @return
     */
    @Singleton
    @Provides
    fun provideOkhttpClient(headerInterceptor: Interceptor): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(NetworkConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(NetworkConstants.READ_TIMEOUT, TimeUnit.SECONDS)
        okhttpClientBuilder.writeTimeout(NetworkConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
//        okhttpClientBuilder.cache(cache)
        okhttpClientBuilder.addInterceptor(headerInterceptor)
        return okhttpClientBuilder.build()
    }

    /**
     * This function us used to Provide header interceptor
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            it.proceed(requestBuilder.build())
        }
    }

//    /**
//     * This function is used to Provide and setup cache
//     *
//     * @param context
//     * @return
//     */
//    @Provides
//    @Singleton
//    internal fun provideCache(context: Context): Cache {
//        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
//        return Cache(httpCacheDirectory, NetworkConstants.CACHE_SIZE_BYTES)
//    }

//    /**
//     * This function is used to Provide application context
//     *
//     * @param application
//     * @return
//     */
//    @Provides
//    @Singleton
//    fun provideContext(application: MyApplication): Context {
//        return application.applicationContext
//    }

    /**
     * This function is used to Provide ApiService
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}