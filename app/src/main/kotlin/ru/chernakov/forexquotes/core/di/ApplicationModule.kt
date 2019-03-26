package ru.chernakov.forexquotes.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.chernakov.forexquotes.App
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

}
