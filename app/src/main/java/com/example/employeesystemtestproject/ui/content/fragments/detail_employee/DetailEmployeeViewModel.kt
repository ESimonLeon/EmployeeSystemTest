package com.example.employeesystemtestproject.ui.content.fragments.detail_employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.employeesystemtestproject.base.BaseViewModel
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.retrofit.responses.ResDetailEmployee
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEmployeeViewModel : BaseViewModel() {

    private var _detailEmployee = MutableLiveData<DataEmployee>()
    var detailEmployee: LiveData<DataEmployee> = _detailEmployee

    private var _successfullyService = MutableLiveData<Boolean>()
    var successfullyService: LiveData<Boolean> = _successfullyService

    fun getDataEmployee(idEmployee: String) = viewModelScope.launch {
        withContext(IO) {
            serviceDetailEmployee(idEmployee)
        }
    }

    private fun serviceDetailEmployee(userId: String) = with(retrofit) {
        getDetailEmployee(userId).enqueue(object : Callback<ResDetailEmployee> {
            override fun onResponse(
                call: Call<ResDetailEmployee>,
                response: Response<ResDetailEmployee>
            ) {
                response.validateResponse()
            }

            override fun onFailure(call: Call<ResDetailEmployee>, t: Throwable) {
                _onFailure.value = t.localizedMessage
            }
        })
    }

    private fun Response<ResDetailEmployee>.validateResponse() {
        if (isSuccessful) {
            val employee = body()
            _successfullyService.value = if (employee?.data != null) {
                _detailEmployee.value = employee.data
                true
            } else false
        } else _successfullyService.value = false
    }
}
