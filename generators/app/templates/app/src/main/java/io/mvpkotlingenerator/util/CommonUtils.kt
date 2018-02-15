package <%= package %>.util

import android.support.v4.app.FragmentManager
import <%= package %>.dialog.CommonLoadingDialog

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

class CommonUtils {
    companion object {
        fun showLoadingDialog(fm: FragmentManager): CommonLoadingDialog {
            val commonDialog: CommonLoadingDialog = CommonLoadingDialog()
            commonDialog.show(fm, CommonLoadingDialog.TAG)
            return CommonLoadingDialog()
        }
    }
}