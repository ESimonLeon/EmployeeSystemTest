package com.example.employeesystemtestproject.retrofit.responses

import com.example.employeesystemtestproject.retrofit.model.DataEmployee

class ResListEmployees {
    var status: String? = null
    var data: ArrayList<DataEmployee> = arrayListOf()
    var message: String? = null
}
