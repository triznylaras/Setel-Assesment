package mobile.setel.com.restaurantlist.model

import com.google.gson.annotations.SerializedName

class RestaurantListData() {
    @SerializedName("restaurants")
    var restaurantList: ArrayList<RestaurantData>? = null

    @SerializedName("timestamp")
    var time: Int? = null

    constructor(restaurantList: ArrayList<RestaurantData>, time: Int) : this() {
        this.restaurantList = restaurantList
        this.time = time
    }
}