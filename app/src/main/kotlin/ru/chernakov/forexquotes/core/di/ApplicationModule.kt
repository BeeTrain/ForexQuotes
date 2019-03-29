package ru.chernakov.forexquotes.core.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.chernakov.forexquotes.App
import ru.chernakov.forexquotes.BuildConfig
import ru.chernakov.forexquotes.core.executor.JobExecutor
import ru.chernakov.forexquotes.core.executor.PostExecutionThread
import ru.chernakov.forexquotes.core.executor.ThreadExecutor
import ru.chernakov.forexquotes.core.executor.UIThread
import ru.chernakov.forexquotes.features.quotes.QuotesRepository
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideQuotesRepository(dataSource: QuotesRepository.Network): QuotesRepository = dataSource

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://forex.1forge.com/1.0.3/")
            .client(createClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

}
