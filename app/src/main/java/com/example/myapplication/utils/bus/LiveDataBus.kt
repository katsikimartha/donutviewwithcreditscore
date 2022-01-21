package com.example.myapplication.utils.bus

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Singleton object to manage bus events.
 */
object LiveDataBus {

    private val subjectMap = HashMap<BusEvent, EventLiveData>()

    /**
     * Get the live data or create it if it's not already in memory.
     */
    @NonNull
    private fun getLiveData(subjectCode: BusEvent): EventLiveData {
        var liveData: EventLiveData? = subjectMap[subjectCode]
        if (liveData == null) {
            liveData = EventLiveData(subjectCode)
            subjectMap[subjectCode] = liveData
        }

        return liveData
    }

    /**
     * Subscribe to the specified subject and listen for updates on that subject.
     */
    fun subscribe(subject: BusEvent, @NonNull lifecycle: LifecycleOwner, @NonNull action: Observer<ConsumableEvent>) {
        try {
            // avoid register same instance
            getLiveData(subject).observe(lifecycle, action)
        } catch (throwable: IllegalArgumentException) {
            throwable.printStackTrace()
        }
    }

    /**
     * Removes this subject when it has no observers.
     */
    fun unregister(subject: BusEvent) {
        subjectMap.remove(subject)
    }

    /**
     * Publish an object to the specified subject for all subscribers of that subject.
     */
    fun publish(subject: BusEvent, message: ConsumableEvent = ConsumableEvent()) {
        getLiveData(subject).update(message)
    }
}