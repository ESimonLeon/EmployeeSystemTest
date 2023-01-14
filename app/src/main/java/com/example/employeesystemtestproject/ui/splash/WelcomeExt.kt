package com.example.employeesystemtestproject.ui.splash

import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleContent
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleLogin
import com.example.employeesystemtestproject.assistant.getEmployeeSharedPreferences

fun WelcomeActivity.starAnimation(): Animation = with(binding) {
    loadAnimation(this@starAnimation, R.anim.zoom_view).apply {
        setAnimationListener(this@starAnimation)
        ivWelcome.animation = this
    }
}

fun WelcomeActivity.finishSplashModule() = with(getEmployeeSharedPreferences()) {
    if (this == null) goToModuleLogin()
    else goToModuleContent()
    finish()
}