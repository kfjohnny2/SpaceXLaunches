package com.example.spacexlaunches.utils.helpers

import android.app.AlertDialog
import android.content.Context

fun showAlertDialog(context: Context, title: String, message: String) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.show()
}