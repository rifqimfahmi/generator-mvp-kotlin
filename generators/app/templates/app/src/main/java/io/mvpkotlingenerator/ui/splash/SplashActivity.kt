package <%= package %>.ui.splash

import android.os.Bundle
import <%= package %>.R
import <%= package %>.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject
    lateinit var mSplashPresenter: SplashMvpPresenter<SplashMvpView>

    override fun getContentLayout(): Int? = null

    override fun setup(savedInstanceState: Bundle?) {
        mActivityComponent.inject(this)
        mSplashPresenter.onAttach(this)

        // do something here. eg. check for app update and open the next activity
    }
}
