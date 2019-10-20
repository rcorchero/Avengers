package com.rcorchero.avengers.platform.extensions

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.rcorchero.avengers.R

fun Context.showMessage(message: String, onClickListener: DialogInterface.OnClickListener) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message).setPositiveButton(this.getString(R.string.accept), onClickListener)
    builder.create().show()
}