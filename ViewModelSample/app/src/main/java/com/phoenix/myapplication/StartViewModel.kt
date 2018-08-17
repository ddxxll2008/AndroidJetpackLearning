package com.phoenix.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel : ViewModel() {
    private val messageStr = MutableLiveData<String>()
    private var clickNumber: Int

    init {
        messageStr.value = "Hello, Jetpack!"
        clickNumber = 0
    }

    fun getMessageStr(): LiveData<String> {
        return messageStr
    }

    fun doAction(): LiveData<String> {
        clickNumber += 1
        messageStr.value = "click  "+ clickNumber
        return messageStr
    }
}
