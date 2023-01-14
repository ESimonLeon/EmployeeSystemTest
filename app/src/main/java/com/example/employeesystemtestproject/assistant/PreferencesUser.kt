package com.example.employeesystemtestproject.assistant

import android.content.Context
import android.content.SharedPreferences
import com.example.employeesystemtestproject.util.DATA_USER
import com.example.employeesystemtestproject.util.PREFERENCES_APP

fun Context.getAppPreferences(): SharedPreferences? =
    getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE)

fun Context.saveEmployeeSharedPreferences(data: String) = with(getAppPreferences()) {
    this ?: return@with
    edit().putString(DATA_USER, data).apply()
}

fun Context.getEmployeeSharedPreferences(): String? {
    return getAppPreferences()?.getString(DATA_USER, null)
}

fun Context.removeEmployeeSharedPreferences() = with(getAppPreferences()) {
    this ?: return@with
    edit().remove(DATA_USER).apply()
}