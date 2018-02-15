package <%= package %>.ui.base

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

interface MvpPresenter<V : MvpView> {

    fun onAttach(view: V)
    fun onDetach()
}