package com.example.employeesystemtestproject.ui.content

import `in`.mayanknagwanshi.imagepicker.ImageSelectActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleLogin
import com.example.employeesystemtestproject.assistant.getEmployeeSharedPreferences
import com.example.employeesystemtestproject.assistant.removeEmployeeSharedPreferences
import com.example.employeesystemtestproject.assistant.saveEmployeeSharedPreferences
import com.example.employeesystemtestproject.databinding.ActivityMainBinding
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.util.loadImageProfileGlide
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        fun newInstance(context: Context) = with(context) {
            Intent(context, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNavigation()

        createComponentsListener()

        loadHeaderDataUser()
    }

    private fun createNavigation() = with(binding.tbContent) {
        setSupportActionBar(this)
        setNavigationIcon(R.drawable.ic_menu)
    }

    private fun createComponentsListener() = with(binding) {
        tbContent.setNavigationOnClickListener {
            validateViewNavigation()
        }

        nvContent.setNavigationItemSelectedListener {
            it.itemId.validateIdItemMenu()
            return@setNavigationItemSelectedListener true
        }
    }

    private fun Int.validateIdItemMenu() = when (this) {
        R.id.navSetting -> openMenuUpdateImage()
        R.id.navLogOut -> closeSession()
        else -> {}
    }

    private fun closeSession() {
        removeEmployeeSharedPreferences()
        goToModuleLogin()
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val filePath = result.data?.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH)
                updateImageProfile(filePath)
                loadHeaderDataUser()
            }
        }

    private fun updateImageProfile(filePath: String?) = with(filePath) {
        this ?: return@with
        val employee =
            Gson().fromJson(getEmployeeSharedPreferences(), DataEmployee::class.java)
        employee.profile_image = this
        saveEmployeeSharedPreferences(Gson().toJson(employee))
    }

    private fun openMenuUpdateImage() {
        Intent(this, ImageSelectActivity::class.java).apply {
            putExtra(ImageSelectActivity.FLAG_COMPRESS, false)//default is true
            putExtra(ImageSelectActivity.FLAG_CAMERA, true)//default is true
            putExtra(ImageSelectActivity.FLAG_GALLERY, true)//default is true
            putExtra(ImageSelectActivity.FLAG_CROP, true)//default is false
            resultLauncher.launch(this)
        }
    }

    private fun loadHeaderDataUser() = with(getEmployeeSharedPreferences()) {
        this ?: return@with
        val employee = Gson().fromJson(this, DataEmployee::class.java)
        getHeaderViewName().text = employee.employee_name
        getHeaderViewImage().loadImageProfileGlide(employee.profile_image)
    }

    private fun getHeaderViewName(): TextView {
        return binding.nvContent.getHeaderView(0).findViewById(R.id.tvUserHeader) as TextView
    }

    private fun getHeaderViewImage(): ImageView {
        return binding.nvContent.getHeaderView(0).findViewById(R.id.ivUserHeader) as ImageView
    }

    private fun validateViewNavigation() = with(binding.dlContent) {
        if (isDrawerOpen(GravityCompat.START)) closeDrawer(GravityCompat.START)
        else openDrawer(GravityCompat.START)
    }

}