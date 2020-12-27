package com.lek.arcade.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State, Event> : ViewModel() {

    private val mutableState = MutableLiveData<State>()
    private val eventLiveData = MutableLiveData<Event>()

    val sate: LiveData<State> = mutableState
    val event: LiveData<Event> = eventLiveData

    fun updateState(newState: State) {
        mutableState.postValue(newState)
    }

    fun updateEvent(event: Event) {
        eventLiveData.postValue(event)
    }
}
