package <%= package %>.ui.base

interface MvpPresenter<V : MvpView> {

    fun onAttach(view: V)
    fun onDetach()
}