package mobile.setel.com.restaurantlist.base

import androidx.annotation.StringRes

/**
 * Created by hendriksaragih on 5/4/18.
 */
interface BaseView {

    fun showLoading()

    fun dismissLoading()

    fun showMessage(message:String)
}