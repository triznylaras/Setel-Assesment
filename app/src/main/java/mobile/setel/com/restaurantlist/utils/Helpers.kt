package mobile.setel.com.restaurantlist.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.tapadoo.alerter.Alerter
import mobile.setel.com.restaurantlist.R

object Helpers {

    fun showToast(context: Context, message: String) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getMaterialProgressDialog(context: Context): MaterialDialog {
        return MaterialDialog.Builder(context)
            .content(R.string.please_wait)
            .limitIconToDefaultSize()
            .title(R.string.loading)
            .autoDismiss(false)
            .canceledOnTouchOutside(false)
            .progress(true, 0)
            .build()
    }

    fun showAlert(activity: Activity, message: String, isError: Boolean) {
        val backgroundColor = if (isError) R.color.orange_red else R.color.colorAccent
        Alerter.create(activity)
            .setText(message)
            .enableVibration(true)
            .enableSwipeToDismiss()
            .setBackgroundColorRes(backgroundColor)
            .show()
    }

    fun showError(activity: Activity, message: String) {
        showAlert(activity, message, true)
    }

    fun showDialogInfo(context: Context, @StringRes titleRes: Int, @StringRes contentRes: Int,
                       singleButtonCallback: MaterialDialog.SingleButtonCallback) {
        MaterialDialog.Builder(context)
            .limitIconToDefaultSize()
            .title(titleRes)
            .content(contentRes)
            .positiveText(R.string.ok_title)
            .negativeText(android.R.string.cancel)
            .autoDismiss(true)
            .canceledOnTouchOutside(false)
            .onPositive(singleButtonCallback)
            .onNegative({ dialog, _ -> dialog.dismiss() })
            .show()
    }
}