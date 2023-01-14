package com.example.employeesystemtestproject.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleContent
import com.example.employeesystemtestproject.assistant.saveEmployeeSharedPreferences
import com.example.employeesystemtestproject.databinding.ActivityLoginBinding
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.retrofit.responses.ResDetailEmployee
import com.example.employeesystemtestproject.util.showAlertDialog
import com.example.employeesystemtestproject.util.showToast
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

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

    private fun setComponentsObserver() = with(viewModel) {
        detailEmployee.observe(this@LoginActivity) { validateDataUser(it) }
        successfullyService.observe(this@LoginActivity) { validateResultService(it) }
        onFailure.observe(this@LoginActivity) { loadFailureService(it) }
    }

    private fun loadFailureService(it: String?) = with(it) {
        this ?: return@with
        loadErrorService()
        showAlertDialog(getString(R.string.error_service_title), this)
    }

    private fun validateResultService(b: Boolean) {
        if (b) goToModuleContent()
        else loadFailureService(getString(R.string.error_description_service))
    }

    private fun loadErrorService() = with(binding){
        mbLogin.isEnabled = true
        pbLoadLogin.visibility = GONE
    }

    private fun validateDataUser(it: ResDetailEmployee?) = with(it) {
        this ?: return@with
        data.let {
            it ?: return@let
            saveUserDataPreferences(it)
        }
    }

    private fun saveUserDataPreferences(it: DataEmployee) =
        saveEmployeeSharedPreferences(Gson().toJson(it))

    private fun setComponentsListener() = with(binding) {
        mbLogin.setOnClickListener { validateFormLogin() }
    }

    private fun validateFormLogin() = with(binding.tietUserId.text) {
        if (this.isNullOrEmpty()) {
            showToast(getString(R.string.user_id_empty_login))
            return@with
        }
        loadProgress()
        initLoginUser(this.toString())
    }

    private fun loadProgress() = with(binding) {
        mbLogin.isEnabled = false
        pbLoadLogin.visibility = VISIBLE
    }

    private fun initLoginUser(userId: String) = viewModel.loginUser(userId)

}