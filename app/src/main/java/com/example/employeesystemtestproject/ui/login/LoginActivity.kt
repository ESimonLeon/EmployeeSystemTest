package com.example.employeesystemtestproject.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesystemtestproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    val viewModel: LoginViewModel by viewModels()

    companion object {
        fun newInstance(context: Context) = with(context) {
            Intent(context, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setComponentsListener()

        setComponentsObserver()
    }

    private fun setComponentsListener() = with(binding) {
        mbLogin.setOnClickListener { validateFormLogin() }
    }

    private fun setComponentsObserver() = with(viewModel) {
        detailEmployee.observe(this@LoginActivity) { validateDataUser(it) }
        successfullyService.observe(this@LoginActivity) { validateResultService(it) }
        onFailure.observe(this@LoginActivity) { loadFailureService(it) }
    }

}