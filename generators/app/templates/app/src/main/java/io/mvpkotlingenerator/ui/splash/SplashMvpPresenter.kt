package <%= package %>.ui.splash

import <%= package %>.di.PerActivity
import <%= package %>.ui.base.MvpPresenter

/*
 * Created by Rifqi Mulya Fahmi on 14/02/18.
 */

@PerActivity
interface SplashMvpPresenter<V: SplashMvpView> : MvpPresenter<V>{

}