package com.example.employeesystemtestproject.base

import androidx.fragment.app.Fragment
import com.example.employeesystemtestproject.util.showAlertDialog

open class BaseFragment : Fragment() {

    fun loadFailureService(it: String?) = with(it) {
        this ?: return@with
        requireContext().showAlertDialog("Fail", this)
    }
}
