package ru.chernakov.forexquotes.core.di

import dagger.Component
import ru.chernakov.forexquotes.App
import ru.chernakov.forexquotes.core.di.viewmodel.ViewModelModule
import ru.chernakov.forexquotes.features.quotes.QuotesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(application: App)

    fun inject(fragment: QuotesFragment)

}
