package <%= package %>.util

import <%= package %>.dialog.CommonLoadingDialog

class CommonUtils {
    companion object {
        fun createLoaderDialog(isBackPressedCancelable: Boolean = true, msg: String? = null): CommonLoadingDialog {
            return CommonLoadingDialog.newInstance(isBackPressedCancelable, msg)
        }
    }
}