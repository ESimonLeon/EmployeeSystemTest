package com.example.employeesystemtestproject.ui.content.fragments.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.employeesystemtestproject.base.BaseFragment
import com.example.employeesystemtestproject.databinding.FragmentListEmployeesBinding
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.ui.content.fragments.employees.adapter.ListEmployeesAdapter

class ListEmployeesFragment : BaseFragment(), ListEmployeesAdapter.OnClickEmployeeItem {

    lateinit var binding: FragmentListEmployeesBinding

    private val viewModel: ListEmployeesViewModel by viewModels()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createBackStack()

        setComponentsObserver()

        setComponentsListener()

        loadListEmployees()
    }

    private fun createBackStack() = with(requireActivity()) {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun loadListEmployees() = viewModel.listEmployees()

    private fun setComponentsListener() = with(binding) {
        srlList.setOnRefreshListener { loadListEmployees() }
    }

    private fun setComponentsObserver() = with(viewModel) {
        listEmployees.observe(requireActivity()) { createRecyclerView(it) }
        onFailure.observe(requireActivity()) { loadFailureService(it) }
    }

    override fun setDataEmployee(employee: DataEmployee) {
        navigateDetailFragment(employee)
    }

    private fun navigateDetailFragment(employee: DataEmployee) =
        ListEmployeesFragmentDirections.actionListEmployeesFragmentToDetailEmployeeFragment(employee.id)
            .apply {
                findNavController().navigate(this)
            }
}