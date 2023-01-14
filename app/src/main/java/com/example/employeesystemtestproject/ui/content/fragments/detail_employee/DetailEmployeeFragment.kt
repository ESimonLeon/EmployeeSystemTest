package com.example.employeesystemtestproject.ui.content.fragments.detail_employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private fun getEmployeeId(idEmployee: String?) = with(idEmployee) {
        this ?: return@with
        viewModel.getDataEmployee(this)
    }
}
