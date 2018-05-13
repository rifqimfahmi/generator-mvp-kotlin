package <%= package %>.ui.base

import android.support.annotation.StringRes

interface MvpView {

    fun showLoading(isBackPressedCancelable: Boolean = true, message: String? = null)

    fun showLoadingWithText(msg: String)

    fun showLoadingWithText(@StringRes msg: Int)

    fun hideLoading()

    fun onError(message: String?)

    fun onError(@StringRes resId: Int)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)
}