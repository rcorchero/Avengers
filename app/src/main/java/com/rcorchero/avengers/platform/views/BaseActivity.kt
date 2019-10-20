package com.rcorchero.avengers.platform.views

import android.content.DialogInterface
import android.os.Bundle
import com.rcorchero.avengers.R
import com.rcorchero.avengers.injector.app.AppComponent
import com.rcorchero.avengers.platform.AvengersApplication
import com.rcorchero.avengers.platform.Navigator
import com.rcorchero.avengers.platform.extensions.showMessage
import com.rcorchero.avengers.platform.widget.SpinnerLoading
import com.rcorchero.avengers.presentation.BaseView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var spinnerLoading: SpinnerLoading

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoading()
    }

    private fun setLayout() {
        setContentView(getActivityLayout())
    }

    protected abstract fun getActivityLayout(): Int

    override fun showLoading() {
        this.let { spinnerLoading.show(this) }
    }

    override fun hideLoading() {
        if (::spinnerLoading.isInitialized) spinnerLoading.dismiss()
    }

    override fun showConnectionError() {
        showMessage(
            getString(R.string.error_has_ocurred),
            DialogInterface.OnClickListener { _, _ -> onCloseDefaultError() })
    }

    override fun showDefaultError() {
        showMessage(
            getString(R.string.connection_error),
            DialogInterface.OnClickListener { _, _ -> onCloseDefaultError() })
    }

    override fun showError(errorDescription: String) {
        if (errorDescription.isBlank()) showDefaultError()
        else showMessage(
            errorDescription,
            DialogInterface.OnClickListener { _, _ -> onCloseDefaultError() })
    }

    open fun onCloseDefaultError() {

    }
}