package com.example.employeesystemtestproject.ui.content.fragments.employees

import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.ui.content.fragments.employees.adapter.ListEmployeesAdapter

fun ListEmployeesFragment.createRecyclerView(it: ArrayList<DataEmployee>?) = with(it) {
    this ?: return@with
    binding.rvEmployees.adapter = ListEmployeesAdapter(this, this@createRecyclerView)

    validateSizeList(size)
}

fun ListEmployeesFragment.validateSizeList(size: Int) {
    if (size > 0) loadNotEmptyList()
    else loadEmptyList()
}

fun ListEmployeesFragment.loadEmptyList() = with(binding) {
    tvEmpty.visibility = VISIBLE
    pbLoad.visibility = GONE
    srlList.isRefreshing = false
}

fun ListEmployeesFragment.loadNotEmptyList() = with(binding) {
    tvEmpty.visibility = GONE
    pbLoad.visibility = GONE
    srlList.isRefreshing = false
}