package com.example.employeesystemtestproject.assistant

import android.content.Context
import com.example.employeesystemtestproject.util.DATA_USER
import com.example.employeesystemtestproject.util.PREFERENCES_APP


fun Context.saveEmployeeSharedPreferences(data: String) =
    getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE).edit()
        .putString(DATA_USER, data).apply()

fun Context.getEmployeeSharedPreferences(): String? =
    getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE).getString(DATA_USER, null)

fun Context.removeEmployeeSharedPreferences() =
     getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE).edit().remove(DATA_USER).apply()
