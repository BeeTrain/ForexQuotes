package ru.chernakov.forexquotes.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.chernakov.forexquotes.core.exception.Failure


abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}