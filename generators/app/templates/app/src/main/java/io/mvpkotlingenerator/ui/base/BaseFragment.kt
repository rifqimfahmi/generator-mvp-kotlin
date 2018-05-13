package <%= package %>.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import <%= package %>.di.component.ActivityComponent
import <%= package %>.dialog.CommonLoadingDialog
import <%= package %>.ui.base.permission.BasePermissionActivity
import <%= package %>.util.CommonUtils

abstract class BaseFragment : Fragment(), MvpView {

    lateinit var mBaseActivity: BaseActivity

    abstract fun getFragmentLayout(): Int?

    abstract fun setUp(view: View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getFragmentLayout()?.let { return inflater.inflate(it, container, false) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mBaseActivity = context
            mBaseActivity.onFragmentAttached()
        } else {
            throw ClassCastException("The activity is not child of BaseActivity")
        }
    }

    override fun showLoading(isBackPressedCancelable: Boolean, message: String?) {
        mBaseActivity.showLoading(isBackPressedCancelable, message)
    }

    override fun showLoadingWithText(msg: String) {
        showLoading(message = msg)
    }

    override fun showLoadingWithText(@StringRes msg: Int) {
        showLoading(message = getString(msg))
    }

    override fun hideLoading() {
        mBaseActivity.hideLoading()
    }

    override fun onError(message: String?) {
        mBaseActivity.onError(message)
    }

    override fun onError(@StringRes resId: Int) {
        mBaseActivity.onError(resId)
    }

    override fun showMessage(message: String?) {
        mBaseActivity.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        mBaseActivity.showMessage(resId)
    }

    fun getActivityComponent(): ActivityComponent {
        return mBaseActivity.mActivityComponent
    }

    fun requestPermissions(requestCode: Int,
                           permissions: Array<String>,
                           reason: String,
                           allowedCallback: () -> Unit,
                           rejectedCallback: () -> Unit = {}
    ) {
        activity?.let {
            if (it is BasePermissionActivity) {
                it.requestPermissions(requestCode, permissions, reason, allowedCallback, rejectedCallback)
            } else {
                throw IllegalStateException("To use request permissions, the parent activity must implement BasePermissionActivity class")
            }
        }
    }
    
    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}