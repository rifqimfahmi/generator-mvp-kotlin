package <%= package %>.ui.base

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
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

    override fun requestThisPermissions(activity: BaseActivity, requestCode: Int, permissions: Array<String>): Boolean {
        var neededPopupPermissions: ArrayList<String> = ArrayList()
        permissions.filterTo(neededPopupPermissions) { ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_DENIED }

        if (neededPopupPermissions.size > 0) {
            ActivityCompat.requestPermissions(activity, neededPopupPermissions.toTypedArray(), requestCode)
            return true
        }

        return false // All permissions has been granted
    }
    
    fun isViewAttached(): Boolean = mMvpView != null
}