package com.ajinkya.formula1.common

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ajinkya.formula1.BuildConfig
import com.google.gson.Gson


fun Any?.log(tag: String = "") {
    if (BuildConfig.DEBUG) Log.v(Constant.TAG, "$tag : $this")
}

fun Activity.toast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun View.toast(msg: String?) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.showIf(flag: Boolean = true) {
    if (flag) visibility = View.VISIBLE
}

fun View.hideIf(flag: Boolean = true) {
    if (flag) visibility = View.GONE
}

fun View.invisibleIf(flag: Boolean = true) {
    if (flag) visibility = View.INVISIBLE
}

val Any.jsonString: String
    get() = try {
        Gson().toJson(this)
    } catch (e: Exception) {
        ""
    }

inline fun String.ifNotEmpty(action: () -> Unit) {
    if (isNotEmpty()) action.invoke()
}

inline fun <T> Collection<T>.ifNotEmpty(action: () -> Unit) {
    if (isEmpty()) action.invoke()
}

inline fun String.ifEmpty(action: () -> Unit) {
    if (isNotEmpty()) action.invoke()
}

inline fun <T> Collection<T>.ifEmpty(action: () -> Unit) {
    if (isEmpty()) action.invoke()
}

fun String?.ifNull(value: String) = this ?: value