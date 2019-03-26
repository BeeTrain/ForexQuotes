package ru.chernakov.forexquotes.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.chernakov.forexquotes.App
import ru.chernakov.forexquotes.core.di.AppComponent
import javax.inject.Inject


abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {}

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
