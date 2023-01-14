package com.example.employeesystemtestproject.retrofit.services

import com.example.employeesystemtestproject.retrofit.route.DETAIL_EMPLOYEE
import com.example.employeesystemtestproject.retrofit.route.LIST_EMPLOYEES
import com.example.employeesystemtestproject.retrofit.responses.ResDetailEmployee
import com.example.employeesystemtestproject.retrofit.responses.ResListEmployees
import com.example.employeesystemtestproject.util.PATH_EMPLOYEE_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicesApi {

    @GET(LIST_EMPLOYEES)
    fun getListEmployees(): Call<ResListEmployees>

    @GET(DETAIL_EMPLOYEE)
    fun getDetailEmployee(@Path(PATH_EMPLOYEE_ID) id: String): Call<ResDetailEmployee>
}