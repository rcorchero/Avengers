package com.rcorchero.avengers.platform.widget

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.rcorchero.avengers.R

class SpinnerLoadingImpl : SpinnerLoading {
    private var progressDialog: ProgressDialog? = null
    private lateinit var progressBar: ProgressBar

    private fun setupSpinnerView(context: Context) {
        progressDialog = ProgressDialog(context)
        progressDialog?.isIndeterminate = true
        progressDialog?.setCancelable(false)
        progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        progressBar = ProgressBar(context)
        progressBar.isIndeterminate = true
        progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
        progressBar.setBackgroundResource(android.R.color.transparent)
    }

    override fun show(context: Context) {
        if (progressDialog == null) setupSpinnerView(context)

        if (progressDialog?.isShowing == false) {
            progressDialog?.show()
            progressDialog?.setContentView(progressBar)
        }
    }

    override fun show(context: Context, listener: SpinnerLoadingListener) {
        progressDialog?.setOnShowListener { listener.onFinishAction() }
        this.show(context)
    }

    override fun dismiss() {
        if (progressDialog?.isShowing == true) progressDialog?.dismiss()
    }

    override fun dismiss(listener: SpinnerLoadingListener) {
        progressDialog?.setOnDismissListener { listener.onFinishAction() }
        this.dismiss()
    }
}

