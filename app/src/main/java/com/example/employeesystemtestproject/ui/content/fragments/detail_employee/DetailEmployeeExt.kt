package com.example.employeesystemtestproject.ui.content.fragments.detail_employee

import android.view.View.VISIBLE
import com.example.employeesystemtestproject.R

fun DetailEmployeeFragment.validateResultService(it: Boolean?) = with(it) {
    this ?: return@with
    if (this) loadViewDetail()
    else loadFailureService(getString(R.string.error_description_service))
}

fun DetailEmployeeFragment.loadViewDetail() = with(binding) {
    tvDetailId.visibility = VISIBLE
    tvDetailName.visibility = VISIBLE
    tvDetailAge.visibility = VISIBLE
    tvDetailSalary.visibility = VISIBLE
}