package <%= package %>.ui.splash

import <%= package %>.data.DataManager
import <%= package %>.ui.base.BasePresenter
import <%= package %>.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V: SplashMvpView> @Inject constructor(dataManager: DataManager,
                                        schedulerProvider: SchedulerProvider,
                                        compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), SplashMvpPresenter<V> {

}