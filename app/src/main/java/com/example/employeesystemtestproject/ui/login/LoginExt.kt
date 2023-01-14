package com.example.employeesystemtestproject.ui.login

import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleContent
import com.example.employeesystemtestproject.assistant.saveEmployeeSharedPreferences
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.retrofit.responses.ResDetailEmployee
import com.example.employeesystemtestproject.util.showAlertDialog
import com.example.employeesystemtestproject.util.showToast
import com.google.gson.Gson

fun LoginActivity.validateFormLogin() = with(binding.tietUserId.text) {
    if (this.isNullOrEmpty()) {
        showToast(getString(R.string.user_id_empty_login))
        return@with
    }
    loadProgress()
    initLoginUser(this.toString())
}

fun LoginActivity.loadProgress() = with(binding) {
    mbLogin.isEnabled = false
    pbLoadLogin.visibility = VISIBLE
}

fun LoginActivity.initLoginUser(userId: String) = viewModel.loginUser(userId)

fun LoginActivity.validateDataUser(it: ResDetailEmployee?) = with(it) {
    this ?: return@with
    data.let {
        it ?: return@let
        saveUserDataPreferences(it)
    }
}

fun LoginActivity.saveUserDataPreferences(it: DataEmployee) =
    saveEmployeeSharedPreferences(Gson().toJson(it))

fun LoginActivity.validateResultService(b: Boolean) {
    if (b) goToModuleContent()
    else loadFailureService(getString(R.string.error_description_service))
}

fun LoginActivity.loadFailureService(it: String?) = with(it) {
    this ?: return@with
    loadViewErrorService()
    showAlertDialog(getString(R.string.error_service_title), this)
}

fun LoginActivity.loadViewErrorService() = with(binding) {
    mbLogin.isEnabled = true
    pbLoadLogin.visibility = GONE
}
