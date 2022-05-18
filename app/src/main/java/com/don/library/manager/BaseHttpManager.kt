package com.don.library.manager

import com.don.library.constants.Tag
import com.don.library.gson.*
import com.don.library.util.LogUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.net.Proxy
import java.util.concurrent.TimeUnit

open class BaseHttpManager {

    protected fun createRetrofit(
        baseUrl: String,
        client: OkHttpClient = createHttpClient(),
        converterFactory: Converter.Factory = GsonConverterFactory.create(
            createGson()
        )
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    }

    protected fun createCoroutineRetrofit(
        baseUrl: String,
        client: OkHttpClient = createHttpClient(),
        converterFactory: Converter.Factory = GsonConverterFactory.create(
            createGson()
        )
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }

    private fun createHttpClient(): OkHttpClient {
        var loggingInterceptor = HttpLoggingInterceptor {
            LogUtil.log(
                Tag.HTTP_MANAGER, it
            )
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor).connectTimeout(1, TimeUnit.MINUTES)
            .connectionPool(ConnectionPool(20, 5, TimeUnit.MINUTES)).proxy(Proxy.NO_PROXY).build()
    }


    protected fun <T> createService(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    private fun createGson(): Gson {
        return GsonBuilder().excludeFieldsWithModifiers(
            Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC
        ).setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().setPrettyPrinting()
            .registerTypeAdapter(String::class.java, StringAdapter())
            .registerTypeAdapter(Long::class.java, LongAdapter())
            .registerTypeAdapter(Int::class.java, IntAdapter())
            .registerTypeAdapter(Float::class.java, FloatAdapter())
            .registerTypeAdapter(Double::class.java, DoubleAdapter())
            .registerTypeAdapter(Boolean::class.java, BooleanAdapter()).create()
    }
}
