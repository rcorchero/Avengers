package com.rcorchero.avengers.platform.widget

import android.content.Context

interface SpinnerLoading {
    fun show(context: Context)
    fun show(context: Context, listener: SpinnerLoadingListener)

    fun dismiss()
    fun dismiss(listener: SpinnerLoadingListener)
}
