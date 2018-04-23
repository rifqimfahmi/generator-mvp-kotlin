package <%= package %>.ui.base.permission
 
class Permission {
    var requestCode: Int = 0
    var askedPermission: Array<String> = arrayOf()
    var reason: String = ""
    var allowedCallback: () -> Unit = {}
    var rejectedCallback: () -> Unit = {}

    fun set(
            requestCode: Int,
            askedPermission: Array<String>,
            reason: String,
            allowedCallback: () -> Unit,
            rejectedCallback: () -> Unit
    ) {
        this.requestCode = requestCode
        this.askedPermission = askedPermission
        this.reason = reason
        this.allowedCallback = allowedCallback
        this.rejectedCallback = rejectedCallback
    }
}
