package <%= package %>.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import <%= package %>.R
import kotlinx.android.synthetic.main.dialog_loading.view.*
 
class CommonLoadingDialog: DialogFragment() {

    companion object {
        const val TAG: String = "dialog_common_loading"
        const val CUSTOM_MESSAGE = "custom_message"

        fun newInstance(message: String?): CommonLoadingDialog {
            val commonLoadingDialog: CommonLoadingDialog = CommonLoadingDialog()
            if (message != null) {
                val bundle: Bundle = Bundle()
                bundle.putString(CUSTOM_MESSAGE, message)
                commonLoadingDialog.arguments = bundle
            }
            return commonLoadingDialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_loading, container, false)
        val message: String? = arguments?.getString(CUSTOM_MESSAGE)
        if (message != null) {
            view.textview_loading.text = message
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnKeyListener { _: DialogInterface, keyCode: Int, keyEvent: KeyEvent ->
            onBackPressed(keyCode, keyEvent)
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    private fun onBackPressed(keyCode: Int, keyEvent: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == KeyEvent.ACTION_UP) {
            dismiss()
            return true
        }
        return false
    }
}