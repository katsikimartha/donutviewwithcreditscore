package com.example.myapplication.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBarStatic(message: Int) {
    showSnackBarStatic(context.getString(message))
}

fun View.showSnackBarStatic(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    getSnackBarTextView(snackBar)?.setTextColor(Color.RED)
    snackBar.setActionTextColor(Color.WHITE)
    snackBar.setAction(android.R.string.ok) { snackBar.dismiss() }
    snackBar.show()
}

private fun getSnackBarTextView(snackBar: Snackbar) = snackBar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)