package com.example.employeesystemtestproject.ui.content

import `in`.mayanknagwanshi.imagepicker.ImageSelectActivity
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.example.employeesystemtestproject.R
import com.example.employeesystemtestproject.assistant.NavigateModules.Companion.goToModuleLogin
import com.example.employeesystemtestproject.assistant.getEmployeeSharedPreferences
import com.example.employeesystemtestproject.assistant.removeEmployeeSharedPreferences
import com.example.employeesystemtestproject.assistant.saveEmployeeSharedPreferences
import com.example.employeesystemtestproject.retrofit.model.DataEmployee
import com.example.employeesystemtestproject.util.loadImageProfileGlide
import com.google.gson.Gson

fun MainActivity.createNavigation() = with(binding.tbContent) {
    setSupportActionBar(this)
    setNavigationIcon(R.drawable.ic_menu)
}

fun MainActivity.validateViewNavigation() = with(binding.dlContent) {
    if (isDrawerOpen(GravityCompat.START)) closeDrawer(GravityCompat.START)
    else openDrawer(GravityCompat.START)
}

fun MainActivity.validateIdItemMenu(itemId: Int) = when (itemId) {
    R.id.navSetting -> openMenuUpdateImage()
    R.id.navLogOut -> closeSession()
    else -> {}
}

fun MainActivity.closeSession() {
    removeEmployeeSharedPreferences()
    goToModuleLogin()
}

fun MainActivity.openMenuUpdateImage() {
    Intent(this, ImageSelectActivity::class.java).apply {
        putExtra(ImageSelectActivity.FLAG_COMPRESS, false)//default is true
        putExtra(ImageSelectActivity.FLAG_CAMERA, true)//default is true
        putExtra(ImageSelectActivity.FLAG_GALLERY, true)//default is true
        putExtra(ImageSelectActivity.FLAG_CROP, true)//default is false
        resultLauncher.launch(this)
    }
}

fun MainActivity.updateImageProfile(filePath: String?) = with(filePath) {
    this ?: return@with
    val employee =
        Gson().fromJson(getEmployeeSharedPreferences(), DataEmployee::class.java)
    employee.profile_image = this
    saveEmployeeSharedPreferences(Gson().toJson(employee))
}

fun MainActivity.loadHeaderDataUser() = with(getEmployeeSharedPreferences()) {
    this ?: return@with
    val employee = Gson().fromJson(this, DataEmployee::class.java)
    getHeaderViewName().text = employee.employee_name
    getHeaderViewImage().loadImageProfileGlide(employee.profile_image)
}

fun MainActivity.getHeaderViewName(): TextView {
    return binding.nvContent.getHeaderView(0).findViewById(R.id.tvUserHeader) as TextView
}

fun MainActivity.getHeaderViewImage(): ImageView {
    return binding.nvContent.getHeaderView(0).findViewById(R.id.ivUserHeader) as ImageView
}