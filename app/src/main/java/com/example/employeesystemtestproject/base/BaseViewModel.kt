package com.example.employeesystemtestproject.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeesystemtestproject.retrofit.RetrofitApiConfig.Companion.provideRetrofit
import com.example.employeesystemtestproject.retrofit.services.ServicesApi

open class BaseViewModel : ViewModel() {

    var _onFailure = MutableLiveData<String>()
    var onFailure: LiveData<String> = _onFailure

    val retrofit: ServicesApi by lazy { provideRetrofit() }

}
