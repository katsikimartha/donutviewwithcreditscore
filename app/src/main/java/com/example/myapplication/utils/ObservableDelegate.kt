package com.example.myapplication.utils

import androidx.databinding.BaseObservable
import kotlin.reflect.KProperty

class ObservableDelegate<T>(private val id: Int, initialValue: T) {

    var value = initialValue

    operator fun getValue(self: BaseObservable, prop: KProperty<*>): T = value

    operator fun setValue(self: BaseObservable, prop: KProperty<*>, value: T) {
        if (value != this.value) {
            this.value = value
            self.notifyPropertyChanged(id)
        }
    }
}