package com.example.employeesystemtestproject.binding_adapter

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("ageTextColor")
fun changeAgeTextColor(textView: TextView?, age: Int?) {
    textView ?: return
    age ?: return

    val color = if (age in 26..34) Color.GREEN else Color.RED
    textView.setTextColor(color)
}

@BindingAdapter("salaryTextColor")
fun changeSalaryTextColor(textView: TextView?, salary: Int?) {
    textView ?: return
    salary ?: return

    val color = if (salary > 1000) Color.GREEN else Color.RED
    textView.setTextColor(color)
}