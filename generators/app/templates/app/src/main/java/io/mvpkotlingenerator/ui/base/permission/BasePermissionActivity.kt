package <%= package %>.ui.base.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.Window
import <%= package %>.R
import <%= package %>.ui.base.BaseActivity
import <%= package %>.util.AppConstants
 
abstract class BasePermissionActivity : BaseActivity() {

    private val mPermission = Permission()

    fun requestPermissions(requestCode: Int,
                                    permissions: Array<String>,
                                    reason: String,
                                    allowedCallback: () -> Unit,
                                    rejectedCallback: () -> Unit = {}) {

        // Below M doesn't have to ask runtime permission
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ) {
            return
        }

        var allGranted = true // Assume that all permission has been granted
        var needExplanation = false // Assume that all permission never rejected
        val declaredPermission = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions

        mPermission.set(requestCode, permissions, reason, allowedCallback, rejectedCallback)

        for (permission in permissions) {
            if (declaredPermission.indexOf(permission) == -1) {
                throw IllegalStateException("The permission $permission must also be declared in your manifest")
            }

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    needExplanation = true
                }
            }
        }

        if (allGranted) {
            mPermission.allowedCallback()
            return
        }

        if (needExplanation) {
            showRationaleDialog()
        } else {
            askPermission()
        }
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(this, mPermission.askedPermission, mPermission.requestCode)
    }

    private fun showRationaleDialog() {
        val dialog = AlertDialog.Builder(this)
                .setMessage(mPermission.reason)
                .setPositiveButton(R.string.ok_btn, {_, _ -> askPermission()})
                .setNegativeButton(R.string.cancel_btn, { _, _-> mPermission.rejectedCallback() })
                .create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == mPermission.requestCode) {
            var allGranted = true

            if (grantResults.isNotEmpty()) {
                for (grantResult in grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false
                    }
                }
            }

            if (allGranted) {
                mPermission.allowedCallback()
            } else {
                var needExplanation = true
                for (permission in permissions) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        needExplanation = false
                    }
                }

                if (!needExplanation) {
                    onPermissionPermanentlyRejected()
                } else {
                    mPermission.rejectedCallback()
                }
            }
        }
    }

    private fun onPermissionPermanentlyRejected() {
        AlertDialog.Builder(this)
                .setTitle(R.string.dialog_permanently_rejected_title)
                .setMessage(R.string.dialog_permanently_rejected_message)
                .setPositiveButton(R.string.ok_btn, {_, _ -> goToAppSetting()})
                .setNegativeButton(R.string.cancel_btn, { _, _-> mPermission.rejectedCallback() })
                .create()
                .show()
    }

    private fun goToAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, AppConstants.INTENT_REQUEST_SETTING_PERMISSION)
    }
}