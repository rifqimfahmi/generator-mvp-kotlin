package <%= package %>.ui.splash

import android.os.Bundle
import <%= package %>.R
import <%= package %>.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject
    lateinit var mSplashPresenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mActivityComponent.inject(this)
        mSplashPresenter.onAttach(this)
    }

    override fun setup() {

    }

}
