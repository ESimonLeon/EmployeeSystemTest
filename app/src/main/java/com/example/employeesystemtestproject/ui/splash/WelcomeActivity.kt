package com.example.employeesystemtestproject.ui.splash

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleContent
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleLogin
import com.example.employeesystemtestproject.assistant.getEmployeeSharedPreferences
import com.example.employeesystemtestproject.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        starAnimation()
    }

    private fun starAnimation() = with(binding) {
        loadAnimation(this@WelcomeActivity, R.anim.zoom_view).apply {
            setAnimationListener(this@WelcomeActivity)
            ivWelcome.animation = this
        }
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) = finishSplashModule()

    override fun onAnimationRepeat(animation: Animation?) {
    }

    private fun finishSplashModule() = with(getEmployeeSharedPreferences()) {
        if (this == null) goToModuleLogin()
        else goToModuleContent()
        finish()
    }
}