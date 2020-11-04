package mobile.setel.com.restaurantlist.contract

import mobile.setel.com.restaurantlist.base.BasePresenter
import mobile.setel.com.restaurantlist.base.BaseView
import mobile.setel.com.restaurantlist.model.RestaurantListData

class RestaurantListContract {
    interface View : BaseView {
        fun onSuccess(data: RestaurantListData)
        fun onFailed(message: String)
    }

    interface Presenter {
        fun getRestaurantList()
    }
}