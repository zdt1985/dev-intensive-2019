package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

    fun Activity.hideKeyboard() {
    val focus = this.currentFocus
    focus?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
            it.hideSoftInputFromWindow(focus.windowToken, 0)
           }
        }
    }

    fun Activity.isKeyboardOpen(): Boolean {
        val r = Rect()
        window.decorView.getWindowVisibleDisplayFrame(r)
        return window.decorView.height - (r.bottom - r.top) > window.decorView.height / 4
    }

    fun Activity.isKeyboardClosed(): Boolean {
        return !isKeyboardOpen()
    }

