package com.ajinkya.formula1.common

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.BuildConfig
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

internal val Any.jsonString: String
    get() = try {
        Gson().toJson(this)
    } catch (e: Exception) {
        ""
    }