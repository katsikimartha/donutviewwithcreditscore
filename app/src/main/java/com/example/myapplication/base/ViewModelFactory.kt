@file:Suppress("UNCHECKED_CAST")

package com.example.myapplication.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider

@Reusable
class ViewModelFactory @Inject constructor(
        private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModels[modelClass]
        return if (provider != null) {
            provider.get() as T
        } else {
            throw IllegalArgumentException("Unknown class name ${modelClass.simpleName}")
        }
    }
}