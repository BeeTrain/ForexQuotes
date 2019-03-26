package ru.chernakov.forexquotes

import android.app.Application
import ru.chernakov.forexquotes.core.di.AppComponent
import ru.chernakov.forexquotes.core.di.ApplicationModule
import ru.chernakov.forexquotes.core.di.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = component.inject(this)

}