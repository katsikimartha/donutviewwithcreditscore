package com.example.myapplication.utils.bus

/**
 *  A consumable event, make sure event can run only one time
 *  If you don't consume it, an event can be run multiple times
 */
data class ConsumableEvent(private var data: Any? = null, var isConsumed: Boolean = false) {
	fun consume(): Any? {
		isConsumed = true
        return data
    }
}