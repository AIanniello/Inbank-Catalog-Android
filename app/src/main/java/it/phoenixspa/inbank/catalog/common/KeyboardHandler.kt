package it.phoenixspa.inbank.catalog.common

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardHandler {
    companion object {
        fun showKeyboard(view: View, activity: Activity) {
            Handler(Looper.getMainLooper()).postDelayed({
                view.requestFocus()
                val methodManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                methodManager.showSoftInput(activity.currentFocus, InputMethodManager.SHOW_FORCED)
            }, 50)
        }

        fun hideKeyboard(activity: Activity) {
            activity.let {
                val methodManager =
                    it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                activity.currentFocus?.windowToken?.let { binder ->
                    methodManager.hideSoftInputFromWindow(binder, 0)
                }
            }
        }

        fun hideKeyboard(v: View) {
            val inputManager = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(v.windowToken, 0)
        }

    }
}