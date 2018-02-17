package <%= package %>.ui.splash

import <%= package %>.di.PerActivity
import <%= package %>.ui.base.MvpPresenter

@PerActivity
interface SplashMvpPresenter<V: SplashMvpView> : MvpPresenter<V>{

}