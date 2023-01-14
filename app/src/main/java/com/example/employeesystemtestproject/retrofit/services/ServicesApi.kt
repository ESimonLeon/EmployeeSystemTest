package com.example.employeesystemtestproject.retrofit.services

import com.example.employeesystemtestproject.retrofit.responses.ResDetailEmployee
import com.example.employeesystemtestproject.retrofit.responses.ResListEmployees
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicesApi {

    @GET("employees")
    fun getListEmployees(): Call<ResListEmployees>

    @GET("employee/{id}")
    fun getDetailEmployee(@Path("id") id: String): Call<ResDetailEmployee>
}