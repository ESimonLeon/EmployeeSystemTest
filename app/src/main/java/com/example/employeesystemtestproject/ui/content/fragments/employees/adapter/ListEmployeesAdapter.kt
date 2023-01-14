package com.example.employeesystemtestproject.ui.content.fragments.employees.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeesystemtestproject.databinding.ItemListEmployeeBinding
import com.example.employeesystemtestproject.retrofit.model.DataEmployee

class ListEmployeesAdapter(
    private val dataEmployees: ArrayList<DataEmployee>,
    private val listenerEmployee: OnClickEmployeeItem
) :
    RecyclerView.Adapter<ListEmployeesAdapter.ListEmployeesHolder>() {

    interface OnClickEmployeeItem {
        fun setDataEmployee(employee: DataEmployee)
    }

    class ListEmployeesHolder(val binding: ItemListEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEmployeesHolder {
        return ListEmployeesHolder(
            ItemListEmployeeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ListEmployeesHolder, position: Int) =
        with(holder.binding) {
            employeeLayout = dataEmployees[position]
            litenerEmployeeLayout = listenerEmployee
        }

    override fun getItemCount(): Int = dataEmployees.size

}
