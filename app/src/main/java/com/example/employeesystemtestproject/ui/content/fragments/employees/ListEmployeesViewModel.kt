package com.example.employeesystemtestproject.ui.content.fragments.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.employeesystemtestproject.base.BaseViewModel
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.retrofit.responses.ResListEmployees
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListEmployeesViewModel : BaseViewModel() {

    private var _listEmployees = MutableLiveData<ArrayList<DataEmployee>>()
    var listEmployees: LiveData<ArrayList<DataEmployee>> = _listEmployees

    fun listEmployees() = viewModelScope.launch {
        withContext(IO) {
            serviceListEmployees()
        }
    }

    private fun serviceListEmployees() = with(retrofit) {
        getListEmployees().enqueue(object : Callback<ResListEmployees> {
            override fun onResponse(
                call: Call<ResListEmployees>,
                response: Response<ResListEmployees>
            ) {
                val listResponse = arrayListOf<DataEmployee>()
                if (response.isSuccessful) {
                    val responseList = response.body()!!
                    listResponse.addAll(responseList.data)
                }
                _listEmployees.value = listResponse
            }

            override fun onFailure(call: Call<ResListEmployees>, t: Throwable) {
                _onFailure.value = t.localizedMessage
            }
        })
    }

}
