package <%= package %>.util

import android.support.v4.app.FragmentManager
import <%= package %>.dialog.CommonLoadingDialog

class CommonUtils {
    companion object {
        fun showLoadingDialog(fm: FragmentManager): CommonLoadingDialog {
            val commonDialog: CommonLoadingDialog = CommonLoadingDialog()
            commonDialog.show(fm, CommonLoadingDialog.TAG)
            return commonDialog
        }
    }
}