package com.example.employeesystemtestproject.ui.splash

import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesystemtestproject.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        starAnimation()
    }

    override fun onAnimationStart(animation: Animation?) {}

    override fun onAnimationRepeat(animation: Animation?) {}

    override fun onAnimationEnd(animation: Animation?) = finishSplashModule()

}