package com.example.employeesystemtestproject.ui.content.fragments.detail_employee

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.base.BaseFragment
import com.example.employeesystemtestproject.databinding.FragmentDetailEmployeeBinding

class DetailEmployeeFragment : BaseFragment() {

    lateinit var binding: FragmentDetailEmployeeBinding

    private val viewModel: DetailEmployeeViewModel by viewModels()

    private val args: DetailEmployeeFragmentArgs by navArgs()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEmployeeBinding.inflate(inflater, container, false)
        binding.viewModelLayout = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createBackStack()

        createComponentsObserver()

        getEmployeeId(args.idUser.toString())

    }

    private fun createBackStack() = with(requireActivity()) {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun createComponentsObserver() = with(viewModel) {
        successfullyService.observe(requireActivity()) { validateResultService(it) }
        onFailure.observe(requireActivity()) { loadFailureService(it) }
    }

    private fun validateResultService(it: Boolean?) = with(it) {
        this ?: return@with
        if (this) loadViewDetail()
        else loadFailureService(getString(R.string.error_description_service))
    }

    private fun loadViewDetail() = with(binding) {
        tvDetailId.visibility = VISIBLE
        tvDetailName.visibility = VISIBLE
        tvDetailAge.visibility = VISIBLE
        tvDetailSalary.visibility = VISIBLE
    }

    private fun getEmployeeId(idEmployee: String?) = with(idEmployee) {
        this ?: return@with
        viewModel.getDataEmployee(this)
    }
}

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