package ru.chernakov.forexquotes.features.quotes

import android.os.Bundle
import ru.chernakov.forexquotes.R
import ru.chernakov.forexquotes.core.platform.BaseFragment

class QuotesFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_quotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

    }
}