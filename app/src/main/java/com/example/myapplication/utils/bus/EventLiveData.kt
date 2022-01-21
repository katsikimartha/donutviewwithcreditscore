package com.example.myapplication.utils.bus

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * A custom LiveData which can unregister when there is no observer
 */
class EventLiveData(private val mSubject: BusEvent) : LiveData<ConsumableEvent>() {

    fun update(obj: ConsumableEvent) {
        postValue(obj)
    }

    override fun removeObserver(observer: Observer<in ConsumableEvent>) {
        synchronized(observer) {
            if (!hasObservers()) {
                super.removeObserver(observer)
                LiveDataBus.unregister(mSubject)
            }
        }
    }
}