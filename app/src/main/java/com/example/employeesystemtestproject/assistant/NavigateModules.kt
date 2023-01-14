package com.example.employeesystemtestproject.assistant

import android.app.Activity
import com.example.employeesystemtestproject.ui.content.MainActivity
import com.example.employeesystemtestproject.ui.login.LoginActivity

class NavigateModules {

    companion object {

        fun Activity.goToModuleLogin() = LoginActivity.newInstance(this)

        fun Activity.goToModuleContent() = MainActivity.newInstance(this)

    }
}