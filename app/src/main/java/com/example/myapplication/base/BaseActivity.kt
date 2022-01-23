package com.example.myapplication.base

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    protected var shouldColorStatusBar = true
    private var menu: Menu? = null

    private var dialog: AlertDialog? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        dialog?.takeIf { it.isShowing }?.dismiss()
        dialog = null

        super.onPause()
    }
}