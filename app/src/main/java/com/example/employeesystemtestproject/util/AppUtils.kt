package com.example.employeesystemtestproject.util

import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.employeesystemtestproject.R

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showAlertDialog(title: String, message: String) =
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton("Cerrar") { dialog, _ ->
            dialog.dismiss()
        }
        create()
        show()
    }


fun ImageView.loadImageProfileGlide(url: String) {
    val placeholder = context.loadImagePlaceholder()
    placeholder.start()

    Glide.with(this.context).apply {

        clear(this@loadImageProfileGlide)

        this.load(url)
            .placeholder(placeholder)
            .apply(glideOptions(R.drawable.ic_usuario))
            .transform(CircleCrop())
            .into(this@loadImageProfileGlide)
    }

}

fun Context.loadImagePlaceholder(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    return circularProgressDrawable
}

fun glideOptions(errorDrawable: Int): RequestOptions {
    return RequestOptions()
        .centerInside()
        .error(errorDrawable)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
}