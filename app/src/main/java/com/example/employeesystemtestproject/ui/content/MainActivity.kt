package com.example.employeesystemtestproject.ui.content

import `in`.mayanknagwanshi.imagepicker.ImageSelectActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.employeesystemtestproject.databinding.ActivityMainBinding

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

    private fun createComponentsListener() = with(binding) {
        tbContent.setNavigationOnClickListener { validateViewNavigation() }
        nvContent.setNavigationItemSelectedListener {
            validateIdItemMenu(it.itemId)
            return@setNavigationItemSelectedListener true
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val filePath = result.data?.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH)
                updateImageProfile(filePath)
                loadHeaderDataUser()
            }
        }

}