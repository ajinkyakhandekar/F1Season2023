package com.ajinkya.formula1.common

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.viewbinding.BuildConfig
import com.ajinkya.formula1.common.Constant.TAG
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

fun Any?.log(tag: String = "") {
    if (BuildConfig.DEBUG) Log.v(Constant.TAG, "$tag : $this")
}

fun Activity.toast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
