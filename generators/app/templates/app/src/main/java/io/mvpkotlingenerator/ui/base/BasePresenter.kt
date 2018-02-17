package <%= package %>.ui.base

import <%= package %>.data.DataManager
import <%= package %>.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenter<V : MvpView> @Inject constructor(dataManager: DataManager,
                                                     schedulerProvider: SchedulerProvider,
                                                     compositeDisposable: CompositeDisposable)
    : MvpPresenter<V> {

    val mDataManager: DataManager = dataManager
    val mSchedulerProvider: SchedulerProvider = schedulerProvider
    val mCompositeDisposable: CompositeDisposable = compositeDisposable

    var mMvpView: V? = null

    override fun onAttach(view: V) {
        mMvpView = view
    }

    override fun onDetach() {
        mCompositeDisposable.dispose()
        mMvpView = null
    }

    fun isViewAttached(): Boolean = mMvpView != null
}