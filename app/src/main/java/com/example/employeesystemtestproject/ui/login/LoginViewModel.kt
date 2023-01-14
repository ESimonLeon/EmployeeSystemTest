package com.example.employeesystemtestproject.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.employeesystemtestproject.base.BaseViewModel
import com.example.employeesystemtestproject.retrofit.responses.ResDetailEmployee
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

    private var _detailEmployee = MutableLiveData<ResDetailEmployee>()
    var detailEmployee: LiveData<ResDetailEmployee> = _detailEmployee

    private var _successfullyService = MutableLiveData<Boolean>()
    var successfullyService: LiveData<Boolean> = _successfullyService


    fun loginUser(userId: String) = viewModelScope.launch {
        withContext(IO) {
            serviceUserLogin(userId)
        }
    }

    private fun serviceUserLogin(userId: String) = with(retrofit) {
        getDetailEmployee(userId).enqueue(object : Callback<ResDetailEmployee> {
            override fun onResponse(
                call: Call<ResDetailEmployee>,
                response: Response<ResDetailEmployee>
            ) {
                if (response.isSuccessful) {
                    val employee = response.body()
                    _successfullyService.value = (employee?.data != null)
                    _detailEmployee.value = employee!!
                } else _successfullyService.value = false
            }

            override fun onFailure(call: Call<ResDetailEmployee>, t: Throwable) {
                _onFailure.value = t.localizedMessage
                Log.wtf(javaClass.simpleName, " onFailure ${t.localizedMessage}")
            }
        })
    }

}
