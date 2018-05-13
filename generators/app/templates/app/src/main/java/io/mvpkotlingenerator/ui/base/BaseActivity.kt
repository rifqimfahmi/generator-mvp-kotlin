package <%= package %>.ui.base

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import <%= package %>.MyApplication
import <%= package %>.di.component.ActivityComponent
import <%= package %>.di.component.DaggerActivityComponent
import <%= package %>.di.module.ActivityModule
import <%= package %>.dialog.CommonLoadingDialog
import <%= package %>.util.CommonUtils

abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback {

    private var mCommonLoadingDialog: CommonLoadingDialog? = null

    lateinit var mActivityComponent: ActivityComponent

    abstract fun getContentLayout(): Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContentLayout()?.let { setContentView(it) }

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as MyApplication).mAppComponent)
                .build()
        setup(savedInstanceState)
    }

    override fun showLoading(isBackPressedCancelable: Boolean, message: String?) {
        mCommonLoadingDialog?.let {
            if (it.isVisible) {
                hideLoading()
            }
        }
        mCommonLoadingDialog = CommonUtils.createLoaderDialog(msg = message)
        mCommonLoadingDialog?.show(supportFragmentManager, CommonLoadingDialog.TAG)
    }

    override fun showLoadingWithText(msg: String) {
        showLoading(message = msg)
    }

    override fun showLoadingWithText(@StringRes msg: Int) {
        showLoading(message = getString(msg))
    }

    override fun hideLoading() {
        mCommonLoadingDialog?.dismiss()
    }

    private fun showSnackBar(message: String) {
        val snackbar: Snackbar = Snackbar.make(
                findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val sbView: View = snackbar.view
        val textView: TextView = sbView.findViewById(android.support.design.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    override fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar("Some error occurred!")
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Some error occured", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    abstract fun setup(savedInstanceState: Bundle?)
}