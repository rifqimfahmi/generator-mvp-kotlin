package <%= package %>.ui.base

interface MvpPresenter<V : MvpView> {

    fun onAttach(view: V)
    fun onDetach()
    fun requestThisPermissions(activity: BaseActivity, requestCode: Int, permissions: Array<String>): Boolean
    
}