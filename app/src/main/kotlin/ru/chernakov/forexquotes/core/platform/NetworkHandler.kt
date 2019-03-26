package ru.chernakov.forexquotes.core.platform

import android.content.Context
import ru.chernakov.forexquotes.core.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}